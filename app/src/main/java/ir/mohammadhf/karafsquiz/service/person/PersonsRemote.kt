package ir.mohammadhf.karafsquiz.service.person

import io.reactivex.Single
import ir.mohammadhf.karafsquiz.model.data.ApiPerson
import retrofit2.http.GET

interface PersonsRemote {

    @GET("android-test")
    fun getAll(): Single<List<ApiPerson>>
}