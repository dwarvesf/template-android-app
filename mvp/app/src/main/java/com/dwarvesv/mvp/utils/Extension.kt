package {{packageName}}.utils

import android.content.Context
import android.net.ConnectivityManager
import {{packageName}}.R

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

fun isNetworkConnected(context: Context?): Boolean {
    val activeNetwork = (context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnectedOrConnecting
}

fun checkEnableLogin(animFileName: String): Boolean {
    if (animFileName.equals("ice_cream_animation.json"))
        return true
    return false
}