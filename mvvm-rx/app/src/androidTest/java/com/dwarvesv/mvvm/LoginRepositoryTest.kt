package {{packageName}}

import android.support.test.InstrumentationRegistry
import {{packageName}}.data.local.user.UserLocalDataSource
import {{packageName}}.data.request.LoginRequest
import {{packageName}}.repository.UserRepository
import {{packageName}}.service.UserService
import org.junit.Before
import org.junit.Test

class LoginRepositoryTest {

    private lateinit var objectUnderTest: UserRepository

    private val CORRECT_LOGIN = "thanh90@gmail.com"
    private val CORRECT_PASSWORD = "Qwe123"
    @Before
    fun setUp() {
        objectUnderTest = UserRepository.getInstance(UserService.getInstance().api,
                UserLocalDataSource.getInstance(InstrumentationRegistry.getTargetContext())!!)

    }

    @Test
    fun loginSuccess() {
        //given
        val login = CORRECT_LOGIN
        val password = CORRECT_PASSWORD
        //when
        val result = objectUnderTest.login(LoginRequest(login, password))
        //then
        result.test().await().assertNoErrors()
        result.test().await().assertSubscribed()
    }

    @Test
    fun loginWithWrongPass() {
        //given
        val login = CORRECT_LOGIN
        val password = "anyPassword"
        //when
        val result = objectUnderTest.login(LoginRequest(login, password))
        //then
        result.test().await().assertNoErrors()
        result.test().await().assertSubscribed()
    }

    @Test
    fun loginWithOnlyCorrectPass() {
        //given
        val login = "anyLogin"
        val password = CORRECT_PASSWORD
        //when
        val result = objectUnderTest.login(LoginRequest(login, password))
        //then
        result.test().await().assertNoErrors()
        result.test().await().assertSubscribed()
    }
}