package {{packageName}}

import android.support.test.runner.AndroidJUnit4
import {{packageName}}.utils.CheckValidUtils
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginValidatorTest {

    @Test
    fun emptyPasswordIsInvalid() {
        //when
        val result = CheckValidUtils.isPasswordValid("")
        //then
        assertFalse(result)
    }

    @Test
    fun passwordIsInValid() {
        //when
        val result = CheckValidUtils.isPasswordValid("wert5")
        //then
        assertFalse(result)
    }

    @Test
    fun passwordIsValid() {
        //when
        val result = CheckValidUtils.isPasswordValid("Qwert5")
        //then
        assertTrue(result)
    }

    @Test
    fun emptyEmailIsInvalid() {
        //when
        val result = CheckValidUtils.isValidEmail("")
        //then
        assertFalse(result)
    }

    @Test
    fun emailIsInvalid() {
        //when
        val result = CheckValidUtils.isValidEmail("thanh")
        //then
        assertFalse(result)
    }

    @Test
    fun emailIsValid() {
        //when
        val result = CheckValidUtils.isValidEmail("thanh@gmail.com")
        //then
        assertTrue(result)
    }


}