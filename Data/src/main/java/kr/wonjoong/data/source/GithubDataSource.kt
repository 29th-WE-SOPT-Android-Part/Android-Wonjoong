package kr.wonjoong.data.source

import kr.wonjoong.data.model.GithubData

interface GithubDataSource {
    suspend fun getFollowerList(): List<GithubData>

    suspend fun getRepositoryList(): List<GithubData>
}