package kr.wonjoong.data.source.remote

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import kotlin.reflect.KClass

class RetrofitModule {
    private val soptBaseUrl = "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"
    private val githubBaseUrl = ""
    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val headerInterceptor = Interceptor {
        val request = it.request()
            .newBuilder()
            .addHeader("Content-Type", "application/json")
            .build()
        return@Interceptor it.proceed(request)
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .build()

    private fun provideRetrofit(baseUrl: String) =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    fun <T : Any> createApi(clazz: KClass<T>, networkType: NetworkType): T = when (networkType) {
        NetworkType.SOPT -> provideRetrofit(soptBaseUrl).create(clazz.java)
        NetworkType.GITHUB -> provideRetrofit(githubBaseUrl).create(clazz.java)
    }
}