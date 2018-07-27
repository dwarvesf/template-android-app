package com.dwarvesv.mvp.utils

import android.text.TextUtils
import android.text.TextUtils.isEmpty
import android.util.Patterns
import android.util.Patterns.PHONE
import java.util.regex.Pattern


object CheckValidUtils {

    fun isPasswordValid(textToCheck: CharSequence): Boolean {
        val textPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")
        return textPattern.matcher(textToCheck).matches()
    }

    fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    fun isPhoneNumberValid(phoneNumber: CharSequence): Boolean {
        return !isEmpty(phoneNumber) && PHONE.matcher(phoneNumber).matches() && phoneNumber.length >= 9 && phoneNumber.length <= 14
    }


}
