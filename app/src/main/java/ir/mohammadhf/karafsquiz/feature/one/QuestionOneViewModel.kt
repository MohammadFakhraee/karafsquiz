package ir.mohammadhf.karafsquiz.feature.one

import androidx.lifecycle.ViewModel
import io.reactivex.Single
import ir.mohammadhf.karafsquiz.model.repo.NumRepository

class QuestionOneViewModel(private val numRepository: NumRepository) : ViewModel() {

    fun getList(): Single<List<Int>> = numRepository.getList()
}