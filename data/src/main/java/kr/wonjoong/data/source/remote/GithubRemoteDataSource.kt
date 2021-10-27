package kr.wonjoong.data.source.remote

import kr.wonjoong.data.model.GithubData
import kr.wonjoong.data.source.GithubDataSource

class GithubRemoteDataSource: GithubDataSource {
    override suspend fun getFollowerList(): List<GithubData> {
        // TODO("Not yet implemented")
        return emptyList()
    }

    override suspend fun getRepositoryList(): List<GithubData> {
        // TODO("Not yet implemented")
        return emptyList()
    }
}