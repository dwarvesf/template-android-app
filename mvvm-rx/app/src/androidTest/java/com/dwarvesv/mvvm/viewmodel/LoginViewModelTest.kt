package com.dwarvesv.mvvm.viewmodel

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.dwarvesv.mvvm.data.local.user.UserLocalDataSource
import com.dwarvesv.mvvm.repository.UserRepository
import com.dwarvesv.mvvm.service.UserService
import com.dwarvesv.mvvm.view.login.LoginViewModel
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.locks.ReentrantLock


@RunWith(AndroidJUnit4::class)
class LoginViewModelTest {

    private lateinit var loginViewModel: LoginViewModel
    @Before
    fun setUp() {
        loginViewModel = LoginViewModel(UserRepository.getInstance(UserService.getInstance().api
                , UserLocalDataSource.getInstance(InstrumentationRegistry.getTargetContext())!!))

    }

    @Test
    fun doLogin() {
        val lock = ReentrantLock()
        val condition = lock.newCondition()

        lock.lock()
        try {

            loginViewModel.doLogin(email = "thanhlangtu90@gmail.com", password = "Qwert5")

            loginViewModel.outputs.loginFailure.subscribe {
                assertNotNull(null)
                condition.signal()
            }

            loginViewModel.outputs.loginSuccess.subscribe {
                assertNotNull(it)
                condition.signal()
            }
            condition.await()

        } finally {
            lock.unlock()
        }


    }


}
