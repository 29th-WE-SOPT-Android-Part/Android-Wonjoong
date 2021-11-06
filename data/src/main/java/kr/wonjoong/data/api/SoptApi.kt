package kr.wonjoong.data.api

import retrofit2.Call
import retrofit2.http.POST

data class SignInResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: SignInResponseData
) {
    data class SignInResponseData(
        val id: Int,
        val name: String,
        val email: String
    )
}

interface SoptApi {
    @POST("user/signup")
    fun signIn(
        email: String,
        name: String,
        password: String
    ): Call<SignInResponse>
}