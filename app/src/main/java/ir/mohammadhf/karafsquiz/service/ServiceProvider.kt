package ir.mohammadhf.karafsquiz.service

import android.content.Context
import androidx.room.Room
import ir.mohammadhf.karafsquiz.DataFakeGenerator
import ir.mohammadhf.karafsquiz.model.repo.NumRepository
import ir.mohammadhf.karafsquiz.model.repo.PersonsRepository
import ir.mohammadhf.karafsquiz.service.factory.QuestionOneViewModelFactory
import ir.mohammadhf.karafsquiz.service.factory.QuestionTwoViewModelFactory
import ir.mohammadhf.karafsquiz.service.person.AppDatabase
import ir.mohammadhf.karafsquiz.service.person.PersonsLocal
import ir.mohammadhf.karafsquiz.service.person.PersonsRemote
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ServiceProvider {
    private const val DB_NAME_PERSON = "db_person_api"
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://karafsapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    var personsRemote: PersonsRemote? = null
        get() {
            if (field == null)
                field = retrofit.create(PersonsRemote::class.java)
            return field
        }

    private var networkManager: NetworkManager? = null

    private var database: AppDatabase? = null

    private fun provideAppDatabase(context: Context): AppDatabase {
        if (database == null)
            database = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DB_NAME_PERSON
            ).build()
        return database!!
    }

    private fun provideNetworkManager(context: Context): NetworkManager {
        if (networkManager == null)
            networkManager = NetworkManager(context)
        return networkManager!!
    }

    private var personsLocal: PersonsLocal? = null

    private fun providePersonsLocal(context: Context): PersonsLocal {
        if (personsLocal == null)
            personsLocal = provideAppDatabase(context).personsLocalDao()
        return personsLocal!!
    }

    fun provideQuestionTwoViewModelFactory(context: Context): QuestionTwoViewModelFactory {
        val personsRepository = PersonsRepository(
            provideNetworkManager(context),
            personsRemote!!,
            providePersonsLocal(context)
        )
        return QuestionTwoViewModelFactory(personsRepository)
    }

    fun provideQuestionOneViewModelFactory(): QuestionOneViewModelFactory =
        QuestionOneViewModelFactory(NumRepository(DataFakeGenerator))
}