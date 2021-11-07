package kr.wonjoong.data.api

import retrofit2.http.Body
import retrofit2.http.Headers
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

data class SignInRequestData(
    val email: String,
    val name: String,
    val password: String
)

interface SoptApi {
    @Headers("Content-Type: application/json")
    @POST("user/signup")
    suspend fun signIn(
        @Body body: SignInRequestData
    ): SignInResponse
}