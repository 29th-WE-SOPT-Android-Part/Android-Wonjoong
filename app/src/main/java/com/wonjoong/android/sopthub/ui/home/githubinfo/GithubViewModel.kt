package com.wonjoong.android.sopthub.ui.home.githubinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.ui.home.githubinfo.data.FollowerData
import com.wonjoong.android.sopthub.util.notifyObserver

class GithubViewModel : ViewModel() {
    private val _followerList = MutableLiveData<MutableList<FollowerData>>(mutableListOf())
    val followerList: LiveData<MutableList<FollowerData>> get() = _followerList

    init {
        getFollowerList()
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

}