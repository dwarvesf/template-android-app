package com.dwarvesv.mvvm.utils

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
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

fun getVisibilityView(isValid: Boolean): Int {
    if (isValid)
        return View.VISIBLE
    return View.GONE
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