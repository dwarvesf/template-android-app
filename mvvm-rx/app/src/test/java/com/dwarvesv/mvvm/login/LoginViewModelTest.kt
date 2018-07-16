package com.dwarvesv.mvvm.login

import com.dwarvesv.mvvm.data.request.LoginRequest
import com.dwarvesv.mvvm.repository.UserRepository
import com.dwarvesv.mvvm.service.UserApi
import com.dwarvesv.mvvm.service.UserService
import com.dwarvesv.mvvm.view.login.LoginViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {


    lateinit var userApi: UserApi

    lateinit var userRepository: UserRepository


    val CORRECT_LOGIN = "thanh90@gmail.com"
    val CORRECT_PASSWORD = "Qwe123"

    private var loginViewModel: LoginViewModel = LoginViewModel(UserRepository.getInstance(UserService.getInstance().api))


    @Before
    fun setUp() {
        userApi = Mockito.mock(UserService.getInstance().api::class.java)
        userRepository = Mockito.mock(UserRepository.getInstance(userApi)::class.java)
        loginViewModel = LoginViewModel(userRepository)

    }

    @Test
    fun doLogin() {
        val login = CORRECT_LOGIN
        val password = CORRECT_PASSWORD


        //when
        val result = userRepository.login(LoginRequest(login, password))

        //then
        result.test().await().assertNoErrors()
        result.test().await().assertSubscribed()

        /*//when
        loginViewModel.doLogin(login, password)
        //then
        verify(loginViewModel.outputs.loginSuccess.test().await()).assertSubscribed()
        verify(loginViewModel.outputs.loginSuccess.test().await()).assertNoErrors()*/
    }


}
