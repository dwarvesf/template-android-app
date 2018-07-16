package com.dwarvesv.mvvm.utils

import com.dwarvesv.mvvm.repository.UserRepository
import com.dwarvesv.mvvm.service.UserApi

object Injection {
    fun provideUserRepository(userApi: UserApi): UserRepository {
        return UserRepository.getInstance(userApi)
    }
}
