package ir.mohammadhf.karafsquiz.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import ir.mohammadhf.karafsquiz.R
import ir.mohammadhf.karafsquiz.databinding.FragmentSelectQuestionBinding

class QuestionListFragment : Fragment() {
    private var _binding: FragmentSelectQuestionBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSelectQuestionBinding.inflate(inflater, container, false)

        binding.run {
            questionOne.setOnClickListener(
                Navigation.createNavigateOnClickListener(
                    R.id.action_questionListFragment_to_questionOneFragment
                )
            )

            questionTwo.setOnClickListener(
                Navigation.createNavigateOnClickListener(
                    R.id.action_questionListFragment_to_questionTwoFragment
                )
            )

            questionThree.setOnClickListener(
                Navigation.createNavigateOnClickListener(
                    R.id.action_questionListFragment_to_questionThreeFragment
                )
            )
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}