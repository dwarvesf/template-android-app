package com.dwarvesv.mvvm.repository

import io.reactivex.Single
import retrofit2.Response

class UserRepository(private val userApi: com.dwarvesv.mvvm.service.UserApi) {

    fun login(request: com.dwarvesv.mvvm.data.request.LoginRequest
    ): Single<Response<com.dwarvesv.mvvm.data.response.LoginResponse>> {
        return userApi.login(request)
    }

    fun getListData(request: com.dwarvesv.mvvm.data.request.GetUsersRequest
    ): Single<Response<ArrayList<com.dwarvesv.mvvm.data.model.User>>> {
        return userApi.getListData(request)
    }

    companion object {

        private var INSTANCE: UserRepository? = null


        @JvmStatic
        fun getInstance(userApi: com.dwarvesv.mvvm.service.UserApi): UserRepository {
            return INSTANCE ?: UserRepository(userApi)
                    .apply { INSTANCE = this }
        }

        /**
         * Used to force [getInstance] to create a new instance
         * next time it's called.
         */
        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}