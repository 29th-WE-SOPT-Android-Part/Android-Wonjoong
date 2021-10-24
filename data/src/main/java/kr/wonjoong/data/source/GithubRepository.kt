package kr.wonjoong.data.source

import kr.wonjoong.data.model.GithubData
import kr.wonjoong.data.source.local.GithubLocalDataSource

class GithubRepository(
    private val githubLocalDataSource: GithubLocalDataSource
) : GithubDataSource {
    override suspend fun getFollowerList(): List<GithubData> {
        return githubLocalDataSource.getFollowerList()
    }

    override suspend fun getRepositoryList(): List<GithubData> {
        return githubLocalDataSource.getRepositoryList()
    }
}