package ir.mohammadhf.karafsquiz.service.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.mohammadhf.karafsquiz.feature.one.QuestionOneViewModel
import ir.mohammadhf.karafsquiz.model.repo.NumRepository

class QuestionOneViewModelFactory(private val numRepository: NumRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuestionOneViewModel::class.java))
            return QuestionOneViewModel(numRepository) as T

        throw IllegalArgumentException("class is not extended from ViewModel")
    }

}