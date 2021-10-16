package com.wonjoong.android.sopthub.ui.home.githubinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.ui.home.githubinfo.data.FollowerData
import com.wonjoong.android.sopthub.ui.home.githubinfo.data.RepositoryData
import com.wonjoong.android.sopthub.util.notifyObserver

class GithubViewModel : ViewModel() {
    private val _followerList = MutableLiveData<MutableList<FollowerData>>(mutableListOf())
    val followerList: LiveData<MutableList<FollowerData>> get() = _followerList
    private val _repositoryList = MutableLiveData<MutableList<RepositoryData>>(mutableListOf())
    val repositoryList: LiveData<MutableList<RepositoryData>> get() = _repositoryList

    init {
        getFollowerList()
        getRepositoryList()
    }

    private fun getFollowerList() {
        _followerList.value?.addAll(
            listOf(
                FollowerData(R.drawable.ic_launcher_background, "문다빈", "안드로이드 어머니"),
                FollowerData(R.drawable.ic_launcher_background, "다빈", "안드 어니"),
                FollowerData(R.drawable.ic_launcher_background, "빈", "안드로드 어머"),
                FollowerData(R.drawable.ic_launcher_background, "문다", "안드로드 어니"),
                FollowerData(R.drawable.ic_launcher_background, "문빈", "안로이드 머니"),
            )
        )
        _followerList.notifyObserver()
    }

    private fun getRepositoryList() {
        _repositoryList.value?.addAll(
            listOf(
                RepositoryData("안드로이드 과제 레포지토리", "재밌다"),
                RepositoryData("ios", "재밌다"),
                RepositoryData("서버", "재밌다 재밌다 재밌다 재밌다 재밌다 재밌다 재밌다 재밌다"),
                RepositoryData("기획", "재밌다 재밌다 재밌다 재밌다 재밌다"),
                RepositoryData("디자인", "재밌다".repeat(100)),
            )
        )
        _repositoryList.notifyObserver()
    }

}