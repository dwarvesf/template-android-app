package {{packageName}}.di.module

import {{packageName}}.BuildConfig
import {{packageName}}.data.api.ProductApi
import {{packageName}}.data.api.ProductApiImpl
import {{packageName}}.data.storage.AuthStorage
import {{packageName}}.di.ApplicationScope
import {{packageName}}.util.AUTHORIZATION
import {{packageName}}.util.BASE_URL
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    @ApplicationScope
    fun provideHttpClient(authStorage: AuthStorage): OkHttpClient {
        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }

        return builder.addInterceptor(accessTokenInterceptor(authStorage))
                .build()
    }

    private fun accessTokenInterceptor(authStorage: AuthStorage): Interceptor {
        return Interceptor {
            val url = it.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter(AUTHORIZATION, authStorage.getAccessToken())
                    .build()

            val request = it.request()
                    .newBuilder()
                    .url(url)
                    .build()

            return@Interceptor it.proceed(request)
        }
    }

    @Provides
    @ApplicationScope
    fun provideProductApi(): ProductApi {
        // In real world, will use Retrofit to create api
        return ProductApiImpl()
    }

    @Provides
    @ApplicationScope
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
}
