package com.dwarvesv.mvvm.login

import com.dwarvesv.mvvm.data.request.LoginRequest
import com.dwarvesv.mvvm.repository.UserRepository
import com.dwarvesv.mvvm.service.UserService
import org.junit.Test

class LoginRepositoryTest {

    val objectUnderTest = UserRepository.getInstance(UserService.getInstance().api)


    val CORRECT_LOGIN = "thanh90@gmail.com"
    val CORRECT_PASSWORD = "Qwe123"

    @Test
    fun `login with correct login and password`() {
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
    fun `do not login with only correct login`() {
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
    fun `do not login with only correct password`() {
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