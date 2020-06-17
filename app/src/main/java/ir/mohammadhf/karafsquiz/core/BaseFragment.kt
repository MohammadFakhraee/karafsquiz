package ir.mohammadhf.karafsquiz.core

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.CompositeDisposable

// Base fragment which will be extended from other fragments.
// Only fragments with observers should extend it.
open class BaseFragment : Fragment() {
    val compositeDisposable = CompositeDisposable()

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
    }

    fun makeSnack(message: String) {
        Snackbar.make(
            requireView(),
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }
}