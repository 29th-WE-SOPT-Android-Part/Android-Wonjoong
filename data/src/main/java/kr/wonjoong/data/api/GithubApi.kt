package kr.wonjoong.data.api

import com.google.gson.annotations.SerializedName
import kr.wonjoong.data.GITHUB_API
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

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
    suspend fun getFollowers(
        @Query("Authorization") authorizationToken: String = GITHUB_API
    ): List<GithubFollowerResponse>

    @GET("users/{userId}")
    suspend fun getUserFollowers(
        @Path("userId") userId: String,
        @Query("Authorization") authorizationToken: String = GITHUB_API
    ): GithubUserFollowerResponse
}