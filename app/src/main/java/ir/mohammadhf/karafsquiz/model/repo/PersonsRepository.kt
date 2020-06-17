package ir.mohammadhf.karafsquiz.model.repo

import io.reactivex.Single
import ir.mohammadhf.karafsquiz.model.data.ApiPerson
import ir.mohammadhf.karafsquiz.service.NetworkManager
import ir.mohammadhf.karafsquiz.service.person.PersonsLocal
import ir.mohammadhf.karafsquiz.service.person.PersonsRemote

class PersonsRepository(
    private val networkManager: NetworkManager,
    private val personsRemote: PersonsRemote,
    private val personsLocal: PersonsLocal
) {
    fun getPersons(): Single<List<ApiPerson>> =
        if (networkManager.isConnectedToInternet)
            personsRemote.getAll()
                .doOnSuccess { apiPeople: List<ApiPerson>? ->
                    if (apiPeople != null) personsLocal.saveAll(apiPeople)
                }
        else personsLocal.getAll()

}