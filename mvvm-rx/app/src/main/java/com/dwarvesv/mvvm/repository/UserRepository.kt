package com.dwarvesv.mvvm.repository

import com.dwarvesv.mvvm.data.model.User
import com.dwarvesv.mvvm.data.request.GetUsersRequest
import com.dwarvesv.mvvm.data.request.LoginRequest
import com.dwarvesv.mvvm.data.response.LoginResponse
import com.dwarvesv.mvvm.service.UserApi
import io.reactivex.Single
import retrofit2.Response

class UserRepository(private val userApi: UserApi) {

    fun login(request: LoginRequest
    ): Single<Response<LoginResponse>> {
        return userApi.login(request)
    }

    fun getListData(request: GetUsersRequest
    ): Single<Response<ArrayList<User>>> {
        return userApi.getListData(request)
    }

    companion object {

        private var INSTANCE: UserRepository? = null


        @JvmStatic
        fun getInstance(userApi: UserApi): UserRepository {
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