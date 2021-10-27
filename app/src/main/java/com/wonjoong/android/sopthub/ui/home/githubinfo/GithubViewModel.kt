package com.wonjoong.android.sopthub.ui.home.githubinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kr.wonjoong.data.model.GithubData
import kr.wonjoong.data.source.GithubRepository

class GithubViewModel(
    private val githubRepository: GithubRepository
) : ViewModel() {
    val followerList: LiveData<List<GithubData>> = liveData {
        val followerList = githubRepository.getFollowerList()
        emit(followerList)
    }
    val repositoryList: LiveData<List<GithubData>> = liveData {
        val repositoryList = githubRepository.getRepositoryList()
        emit(repositoryList)
    }
}