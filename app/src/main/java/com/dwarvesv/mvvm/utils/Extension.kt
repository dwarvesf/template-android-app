package com.dwarvesv.mvvm.utils

import android.content.Context
import android.net.ConnectivityManager
import com.dwarvesv.mvvm.R

fun getErrorHintEmail(isError: Boolean, context: Context): String? {
    if (isError)
        return context.getString(R.string.hint_email)
    return null
}

fun getErrorHintPassword(isError: Boolean, context: Context): String? {
    if (isError)
        return context.getString(R.string.hint_password)
    return null
}


/**
 * Check network status
 */
fun isNetworkConnected(context: Context?): Boolean {
    val fConnectiveManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val fNetworkInfo = fConnectiveManager.activeNetworkInfo

    return (fNetworkInfo != null && fNetworkInfo.isConnected
            && fConnectiveManager.activeNetworkInfo.isAvailable)
}