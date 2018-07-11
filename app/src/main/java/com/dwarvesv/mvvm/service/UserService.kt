package com.dwarvesv.mvvm.service


import com.dwarvesv.mvvm.service.ApiService.Factory.retrofitBuilder
import com.dwarvesv.mvvm.utils.SingletonHolder
import io.reactivex.Single
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserApi {

    @Headers("Content-Type: application/json")
    @POST(com.dwarvesv.mvvm.service.ApiEndpoint.LOGIN)
    fun login(@Body request: com.dwarvesv.mvvm.data.request.LoginRequest
    ): Single<Response<com.dwarvesv.mvvm.data.response.LoginResponse>>

    @POST(com.dwarvesv.mvvm.service.ApiEndpoint.GET_DATA)
    fun getListData(@Body request: com.dwarvesv.mvvm.data.request.GetUsersRequest
    ): Single<Response<ArrayList<com.dwarvesv.mvvm.data.model.User>>>
}

class UserService private constructor() {

    companion object : SingletonHolder<UserService>(::UserService)

    private val apiClient: Retrofit = retrofitBuilder.baseUrl(com.dwarvesv.mvvm.service.ApiEndpoint.BASE_URL).build()

    val api: UserApi = apiClient.create(UserApi::class.java)

}