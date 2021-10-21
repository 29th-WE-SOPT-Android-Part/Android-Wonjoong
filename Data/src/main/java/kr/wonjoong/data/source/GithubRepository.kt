package kr.wonjoong.data.source

import kr.wonjoong.data.R
import kr.wonjoong.data.model.GithubData

class GithubRepository : GithubDataSource {
    override suspend fun getFollowerList(): List<GithubData> {
        val githubProfileList = mutableListOf<GithubData>()
        repeat(10) { num ->
            githubProfileList.add(GithubData("문다빈$num", "안드로이드의 어머니$num", true))
        }
        return githubProfileList
    }
}