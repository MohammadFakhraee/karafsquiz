package ir.mohammadhf.karafsquiz.service.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.mohammadhf.karafsquiz.feature.two.QuestionTwoViewModel
import ir.mohammadhf.karafsquiz.model.repo.PersonsRepository

class QuestionTwoViewModelFactory(private val personsRepository: PersonsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuestionTwoViewModel::class.java))
            return QuestionTwoViewModel(personsRepository) as T

        throw IllegalArgumentException("class is not extended from ViewModel")
    }

}