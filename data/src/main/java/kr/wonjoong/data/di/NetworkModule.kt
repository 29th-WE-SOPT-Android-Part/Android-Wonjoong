package kr.wonjoong.data.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.wonjoong.data.api.GithubApi
import kr.wonjoong.data.api.SoptApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val SOPT_BASE_URL = "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"
    private const val GITHUB_BASE_URL = "https://api.github.com/"

    private val headerInterceptor = Interceptor {
        val request = it.request()
            .newBuilder()
            .addHeader("Content-Type", "application/json")
            .build()
        return@Interceptor it.proceed(request)
    }

    private fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .build()

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private fun provideRetrofit(baseUrl: String) =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @Singleton
    fun provideSoptApi(): SoptApi =
        provideRetrofit(SOPT_BASE_URL).create(SoptApi::class.java)

    @Provides
    @Singleton
    fun provideGithubApi(): GithubApi =
        provideRetrofit(GITHUB_BASE_URL).create(GithubApi::class.java)
}