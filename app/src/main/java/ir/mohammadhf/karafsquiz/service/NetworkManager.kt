package ir.mohammadhf.karafsquiz.service

import android.content.Context
import android.net.ConnectivityManager

class NetworkManager(private val applicationContext: Context) {

    val isConnectedToInternet: Boolean
        get() {
            val connectivityManager = applicationContext
                .getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager

            val netInfo = connectivityManager.activeNetworkInfo
            return netInfo != null && netInfo.isConnected
        }
}