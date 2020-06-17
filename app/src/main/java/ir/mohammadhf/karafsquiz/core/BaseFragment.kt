package ir.mohammadhf.karafsquiz.core

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

// Base fragment which will be extended from other fragments.
// Only fragments with observers should extend it.
open class BaseFragment : Fragment() {
    val compositeDisposable = CompositeDisposable()

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
    }
}