package com.wonjoong.android.sopthub.ui.home.githubinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.ui.home.githubinfo.data.GithubData
import com.wonjoong.android.sopthub.util.notifyObserver

class GithubViewModel : ViewModel() {
    private val _followerList = MutableLiveData<MutableList<GithubData>>(mutableListOf())
    val followerList: LiveData<MutableList<GithubData>> get() = _followerList
    private val _repositoryList = MutableLiveData<MutableList<GithubData>>(mutableListOf())
    val repositoryList: LiveData<MutableList<GithubData>> get() = _repositoryList

    init {
        getFollowerList()
        getRepositoryList()
    }

    private fun getFollowerList() {
        _followerList.value?.addAll(
            listOf(
                GithubData(
                    "문다빈",
                    "안드로이드의 어머니",
                    R.drawable.ic_launcher_background,
                    true
                ),
                GithubData(
                    "문빈",
                    "안드로이드의 어니",
                    R.drawable.ic_launcher_background,
                    true
                ),
                GithubData(
                    "다빈",
                    "안드로이드의 어머",
                    R.drawable.ic_launcher_background,
                    true
                ),
                GithubData(
                    "빈",
                    "ios의 어머니",
                    R.drawable.ic_launcher_background,
                    true
                ),
                GithubData(
                    "빈",
                    "ios의 어머니",
                    R.drawable.ic_launcher_background,
                    true
                ),
                GithubData(
                    "빈",
                    "ios의 어머니",
                    R.drawable.ic_launcher_background,
                    true
                )
            )
        )
        _followerList.notifyObserver()
    }

    private fun getRepositoryList() {
        _repositoryList.value?.addAll(
            listOf(
                GithubData("안드로이드 과제", "재밌따."),
                GithubData("ios 과제", "재밌어.".repeat(20)),
                GithubData("솝트 과제", "재밌군."),
                GithubData("디자인 과제", "재밌네."),
            )
        )
        _repositoryList.notifyObserver()
    }
}