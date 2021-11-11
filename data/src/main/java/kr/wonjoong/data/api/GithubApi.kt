package kr.wonjoong.data.api

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Path

data class GithubFollowerResponse(
    @SerializedName("login")
    val id: String,
    @SerializedName("avatar_url")
    val profileImageUrl: String
)

data class GithubUserFollowerResponse(
    val followers: Int,
    val following: Int
)

interface GithubApi {
    @GET("users/WonJoongLee/followers")
    suspend fun getFollowers(): List<GithubFollowerResponse>

    @GET("users/{userId}")
    suspend fun getUserFollowers(
        @Path("userId") userId: String,
    ): GithubUserFollowerResponse

    @GET("users/{userId}/repos")
    suspend fun getRepositories(
        @Path("userId") userId: String,
    ): GithubUserFollowerResponse
}