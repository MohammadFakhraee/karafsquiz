package ir.mohammadhf.karafsquiz.service.person

import androidx.room.*
import io.reactivex.Single
import ir.mohammadhf.karafsquiz.model.data.ApiPerson

@Dao
interface PersonsLocal {
    @Query("SELECT * FROM ApiPerson")
    fun getAll(): Single<List<ApiPerson>>

    @Insert
    fun saveAll(apiPersonList: List<ApiPerson>)
}

@Database(entities = [ApiPerson::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personsLocalDao(): PersonsLocal
}