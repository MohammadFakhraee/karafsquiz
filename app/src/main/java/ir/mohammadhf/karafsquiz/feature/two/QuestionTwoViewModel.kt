package ir.mohammadhf.karafsquiz.feature.two

import androidx.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import ir.mohammadhf.karafsquiz.model.data.Person
import ir.mohammadhf.karafsquiz.model.repo.PersonsRepository

class QuestionTwoViewModel(private val personsRepository: PersonsRepository) : ViewModel() {
    val loadingBehaviorSub = BehaviorSubject.create<Boolean>()
    fun getNamesList(): Single<List<Person>> {
        loadingBehaviorSub.onNext(true)
        return personsRepository.getPersons()
            .doOnEvent { _, _ ->
                loadingBehaviorSub.onNext(false)
            }
            .map {
                // We should separate api logic from ui logic for future needs.
                // The below code transfers List<ApiPerson>
                // to List<Person>
                val personList = ArrayList<Person>()
                for (firstPerson in it) {
                    val relationList = ArrayList<String>()
                    for (secondPerson in it) {
                        if (firstPerson != secondPerson
                            && firstPerson.isRelatedTo(secondPerson)
                        ) relationList.add(secondPerson.firstName!!)
                    }
                    personList.add(
                        Person(
                            firstPerson.firstName!!,
                            relationList
                        )
                    )
                }
                personList
            }
    }
}