package com.dwarvesv.mvvm.utils

object Injection {
    fun provideUserRepository(userApi: com.dwarvesv.mvvm.service.UserApi): com.dwarvesv.mvvm.repository.UserRepository {
        return com.dwarvesv.mvvm.repository.UserRepository.getInstance(userApi)
    }
}
