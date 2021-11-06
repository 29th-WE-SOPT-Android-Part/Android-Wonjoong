package kr.wonjoong.data.source.remote

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import kotlin.reflect.KClass

class RetrofitModule {
    private val soptBaseUrl = "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"
    private val githubBaseUrl = ""
    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private fun provideRetrofit(baseUrl: String) =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    fun <T : Any> createApi(clazz: KClass<T>, networkType: NetworkType): T = when (networkType) {
        NetworkType.SOPT -> provideRetrofit(soptBaseUrl).create(clazz.java)
        NetworkType.GITHUB -> provideRetrofit(githubBaseUrl).create(clazz.java)
    }
}