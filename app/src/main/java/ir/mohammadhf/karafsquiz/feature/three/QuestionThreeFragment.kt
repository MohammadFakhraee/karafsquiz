package ir.mohammadhf.karafsquiz.feature.three

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.mohammadhf.karafsquiz.R
import ir.mohammadhf.karafsquiz.databinding.FragmentQuestionThreeBinding
import ir.mohammadhf.karafsquiz.model.data.Ruler

class QuestionThreeFragment : Fragment() {
    companion object {
        const val INCH_TO_CENTI = 2.54f
    }

    private val displayMetrics = DisplayMetrics()
    // total pixels per cm
    private var xdpc = 0f
    private var ydpc = 0f

    private val widthAdapter = WidthRulerAdapter()
    private val heightAdapter = HeightRulerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setDefMetrics()

        val binding = FragmentQuestionThreeBinding.inflate(inflater, container, false)
        binding.run {
            widthRulerRv.layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.HORIZONTAL,
                false
            )
            widthRulerRv.adapter = widthAdapter

            heightRulerRv.layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL,
                false
            )
            heightRulerRv.adapter = heightAdapter

            isInch.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) setRulerToInch()
                else setRulerToCm()
            }
        }

        setRulerToInch()

        return binding.root
    }

    private fun setDefMetrics() {
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        xdpc = displayMetrics.xdpi / INCH_TO_CENTI
        ydpc = displayMetrics.ydpi / INCH_TO_CENTI
    }

    private fun setRulerToInch() {
        val rulerNumList =
            arrayListOf(
                Ruler(displayMetrics.xdpi, "1 ${getString(R.string.inches)}"),
                Ruler(displayMetrics.xdpi, "2 ${getString(R.string.inches)}"),
                Ruler(displayMetrics.xdpi, "3 ${getString(R.string.inches)}"),
                Ruler(displayMetrics.xdpi, "4 ${getString(R.string.inches)}")
            )
        widthAdapter.submitList(rulerNumList)
        heightAdapter.submitList(rulerNumList)
    }

    private fun setRulerToCm() {
        val rulerNumList =
            arrayListOf(
                Ruler(xdpc, "1 ${getString(R.string.cm)}"),
                Ruler(xdpc, "2 ${getString(R.string.cm)}"),
                Ruler(xdpc, "3 ${getString(R.string.cm)}"),
                Ruler(xdpc, "4 ${getString(R.string.cm)}")
            )
        widthAdapter.submitList(rulerNumList)
        heightAdapter.submitList(rulerNumList)
    }
}