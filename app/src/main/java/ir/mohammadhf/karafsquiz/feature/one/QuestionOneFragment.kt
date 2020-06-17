package ir.mohammadhf.karafsquiz.feature.one

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ir.mohammadhf.karafsquiz.core.BaseFragment
import ir.mohammadhf.karafsquiz.databinding.FragmentQuestionOneBinding
import ir.mohammadhf.karafsquiz.service.ServiceProvider
import kotlin.math.abs
import kotlin.math.sqrt

class QuestionOneFragment : BaseFragment() {
    private var _binding: FragmentQuestionOneBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var questionOneViewModel: QuestionOneViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestionOneBinding.inflate(inflater, container, false)

        questionOneViewModel = ViewModelProvider(
            requireActivity(),
            ServiceProvider.provideQuestionOneViewModelFactory()
        ).get(QuestionOneViewModel::class.java)

        subscribe()

        return binding.root
    }

    private fun subscribe() {
        questionOneViewModel.getList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<Int>> {
                override fun onSuccess(numList: List<Int>) {
                    val squareLength = sqrt(numList.size.toDouble()).toInt()
                    setInputTxt(numList, squareLength)
                    setOutputTxt(numList, squareLength)
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                }

            })
    }

    private fun setInputTxt(numList: List<Int>, squareLength: Int) {
        var string = ""
        for ((index, value) in numList.withIndex()) {
            string += value.toString()
            string += if ((index + 1) % squareLength == 0) "\n" else "    "
        }
        binding.txtIn.text = string
    }

    private fun setOutputTxt(numList: List<Int>, squareLength: Int) {
        var string = ""

        var isReverse = false
        for (firstIndex in (squareLength - 1) downTo (1 - squareLength)) {
            val totalNumbers = squareLength - abs(firstIndex)
            var currentIndex = if (isReverse) abs(firstIndex) * squareLength
            else firstIndex

            for (i in 1..totalNumbers) {
                string += "${numList[currentIndex]}   "
                currentIndex += (squareLength + 1)
            }
            string += "\n"
            if (firstIndex == 0) isReverse = true
        }

        binding.txtOut.text = string
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}