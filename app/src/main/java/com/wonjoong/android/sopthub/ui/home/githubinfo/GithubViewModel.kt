package com.wonjoong.android.sopthub.ui.home.githubinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wonjoong.android.sopthub.util.notifyObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.wonjoong.data.model.GithubData
import kr.wonjoong.data.source.GithubRepository

class GithubViewModel(
    private val githubRepository: GithubRepository
) : ViewModel() {
    private val _followerList = MutableLiveData<MutableList<GithubData>>(mutableListOf())
    val followerList: LiveData<MutableList<GithubData>> get() = _followerList
    private val _repositoryList = MutableLiveData<MutableList<GithubData>>(mutableListOf())
    val repositoryList: LiveData<MutableList<GithubData>> get() = _repositoryList

    init {
        getFollowerList()
        getRepositoryList()
    }

    private fun getFollowerList() {
        viewModelScope.launch(Dispatchers.IO) {
            val githubProfileList = githubRepository.getFollowerList()
            _followerList.value?.addAll(githubProfileList)
            _followerList.notifyObserver()
        }
    }

    private fun getRepositoryList() {
        viewModelScope.launch(Dispatchers.IO) {
            val githubRepositoryList = githubRepository.getRepositoryList()
            _repositoryList.value?.addAll(githubRepositoryList)
            _repositoryList.notifyObserver()
        }
    }
}