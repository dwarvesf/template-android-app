package com.dwarvesv.mvvm.repository

import com.dwarvesv.mvvm.data.local.user.UserLocalDataSource
import com.dwarvesv.mvvm.data.model.User

import com.dwarvesv.mvvm.data.request.LoginRequest
import com.dwarvesv.mvvm.data.response.LoginResponse
import com.dwarvesv.mvvm.service.UserApi
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import retrofit2.Response
import java.util.concurrent.TimeUnit

class UserRepository(private val userApi: UserApi, private val userLocalDataSource: UserLocalDataSource) {

    fun login(request: LoginRequest
    ): Single<Response<LoginResponse>> {
        return userApi.login(request)
    }

    fun getListData(): Observable<ArrayList<User>> {

        return Observable.concatArrayEager(
                getUsersFromDb(),
                getUsersFromApi()
                        .materialize()
                        .observeOn(AndroidSchedulers.mainThread())
                        .map {
                            // if the observables onError is called, invoke
                            // callback so that presenters can handle error
                            it.error?.let {

                            }
                            // put item back into stream
                            it
                        }
                        .filter { !it.isOnError }
                        .dematerialize<ArrayList<User>>()
                        .debounce(400, TimeUnit.MILLISECONDS)
        )
    }

    fun getUsersFromDb(): Observable<ArrayList<User>> {
        return userLocalDataSource.getAllUsers().map { userList ->
            ArrayList(userList)
        }.toObservable()
    }

    fun getUsersFromApi(): Observable<ArrayList<User>> {
        return userApi.getListData("dwarvesf")
                .doOnSuccess { userList ->
                    //after get user from api, we will store it to local database
                    userLocalDataSource.insertAll(userList)
                }.toObservable()
    }


    companion object {

        private var INSTANCE: UserRepository? = null


        @JvmStatic
        fun getInstance(userApi: UserApi, userLocalDataSource: UserLocalDataSource): UserRepository {
            return INSTANCE ?: UserRepository(userApi, userLocalDataSource)
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