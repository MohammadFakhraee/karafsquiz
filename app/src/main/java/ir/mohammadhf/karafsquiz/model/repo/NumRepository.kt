package ir.mohammadhf.karafsquiz.model.repo

import io.reactivex.Single
import ir.mohammadhf.karafsquiz.DataFakeGenerator

class NumRepository(private val dataFakeGen: DataFakeGenerator) {

    fun getList(): Single<List<Int>> =
        Single.create { emitter ->
            if (!emitter.isDisposed)
                emitter.onSuccess(dataFakeGen.squareArrayOfNum)
        }
}