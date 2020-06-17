package ir.mohammadhf.karafsquiz.feature.two

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ir.mohammadhf.karafsquiz.core.BaseFragment
import ir.mohammadhf.karafsquiz.databinding.FragmentQuestionTwoBinding
import ir.mohammadhf.karafsquiz.model.data.Person
import ir.mohammadhf.karafsquiz.service.ServiceProvider

class QuestionTwoFragment : BaseFragment() {
    private var _binding: FragmentQuestionTwoBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var questionTwoViewModel: QuestionTwoViewModel

    private var personAdapter = PersonAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestionTwoBinding.inflate(inflater, container, false)

        questionTwoViewModel = ViewModelProvider(
            requireActivity(),
            ServiceProvider.provideQuestionTwoViewModelFactory(requireContext())
        ).get(QuestionTwoViewModel::class.java)

        binding.run {
            listRv.layoutManager = LinearLayoutManager(
                context, RecyclerView.VERTICAL, false
            )
            listRv.adapter = personAdapter
        }

        subscribe()

        return binding.root
    }

    private fun subscribe() {
        questionTwoViewModel.getNamesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<Person>> {
                override fun onSuccess(t: List<Person>) {
                    personAdapter.submitList(t)
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }

            })

        compositeDisposable.add(questionTwoViewModel.loadingBehaviorSub
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}