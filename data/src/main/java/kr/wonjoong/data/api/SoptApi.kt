package kr.wonjoong.data.api

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

data class SignUpResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: SignUpResponseData
) {
    data class SignUpResponseData(
        val id: Int,
        val name: String,
        val email: String
    )
}

data class SignUpRequestData(
    val email: String,
    val name: String,
    val password: String
)

interface SoptApi {
    @Headers("Content-Type: application/json")
    @POST("user/signup")
    suspend fun signUp(
        @Body body: SignUpRequestData
    ): SignUpResponse
}