package {{packageName}}.utils


import android.text.TextUtils
import android.util.Patterns
import android.util.Patterns.PHONE
import java.util.regex.Pattern


object CheckValidUtils {

    fun isPasswordValid(textToCheck: CharSequence?): Boolean {
        val textPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")
        return !TextUtils.isEmpty(textToCheck) && textPattern.matcher(textToCheck).matches()
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        val emailPattern = Patterns.EMAIL_ADDRESS
        return !TextUtils.isEmpty(target) && emailPattern.matcher(target).matches()
    }

    fun isPhoneNumberValid(phoneNumber: CharSequence): Boolean {
        return !TextUtils.isEmpty(phoneNumber) && PHONE.matcher(phoneNumber).matches() && phoneNumber.length >= 9 && phoneNumber.length <= 14
    }
}
