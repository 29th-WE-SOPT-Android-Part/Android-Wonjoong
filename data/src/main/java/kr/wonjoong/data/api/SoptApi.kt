package kr.wonjoong.data.api

import kr.wonjoong.data.model.ResponseData
import retrofit2.http.Body
import retrofit2.http.POST

data class SoptApiResponse(
    val responseData: ResponseData,
    val data: SoptApiResponseData
) {
    data class SoptApiResponseData(
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

data class SignInRequestData(
    val email: String,
    val password: String
)

interface SoptApi {
    @POST("user/signup")
    suspend fun signUp(
        @Body body: SignUpRequestData
    ): SoptApiResponse

    @POST("user/login")
    suspend fun signIn(
        @Body body: SignInRequestData
    ): SoptApiResponse
}