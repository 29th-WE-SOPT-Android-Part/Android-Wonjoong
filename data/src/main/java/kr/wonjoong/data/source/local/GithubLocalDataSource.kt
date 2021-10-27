package kr.wonjoong.data.source.local

import kr.wonjoong.data.model.GithubData
import kr.wonjoong.data.source.GithubDataSource

class GithubLocalDataSource: GithubDataSource {
    override suspend fun getFollowerList(): List<GithubData> {
        val githubProfileList = mutableListOf<GithubData>()
        repeat(20) { num ->
            githubProfileList.add(GithubData("문다빈$num", "안드로이드의 어머니$num", true))
        }
        return githubProfileList
    }

    override suspend fun getRepositoryList(): List<GithubData> {
        val githubRepositoryList = mutableListOf<GithubData>()
        repeat(10) { num ->
            githubRepositoryList.add(GithubData("안드 과제$num", "재밌다${num.toString().repeat(10)}"))
        }
        return githubRepositoryList
    }
}