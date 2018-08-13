package {{packageName}}.service

import {{packageName}}.data.model.User
import {{packageName}}.data.request.LoginRequest
import {{packageName}}.data.response.LoginResponse
import {{packageName}}.service.ApiService.Factory.retrofitBuilder
import {{packageName}}.utils.SingletonHolder
import io.reactivex.Single
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.*

interface UserApi {

    @Headers("Content-Type: application/json")
    @POST(ApiEndpoint.LOGIN)
    fun login(@Body request: LoginRequest
    ): Single<Response<LoginResponse>>

    @GET("users/{name}/repos")
    fun getListData(@Path("name") id: String): Single<ArrayList<User>>
}

class UserService private constructor() {

    companion object : SingletonHolder<UserService>(::UserService)

    private val apiClient: Retrofit = retrofitBuilder.baseUrl(ApiEndpoint.BASE_URL).build()

    val api: UserApi = apiClient.create(UserApi::class.java)

}