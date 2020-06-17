package ir.mohammadhf.karafsquiz.service.factory

import android.content.Context

object ExceptionMessageFactory {

    // If server returns any exceptions with json object,
    // we can extend below code, receive error and change it to our model.
    fun injectMessage(throwable: Throwable, context: Context): String {
        return if (throwable.message != null) throwable.message!!
        else "Unknown error occurred!"
    }
}