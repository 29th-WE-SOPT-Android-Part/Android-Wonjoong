package com.wonjoong.android.sopthub.ui.home.githubinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.wonjoong.data.api.GithubApi
import kr.wonjoong.data.api.GithubFollowerResponse
import kr.wonjoong.data.api.GithubUserFollowerResponse
import kr.wonjoong.data.model.GithubData
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
    private val githubApi: GithubApi
) : ViewModel() {

    private val _followerList = MutableLiveData<List<GithubData>>()
    val followerList: LiveData<List<GithubData>> get() = _followerList
    val repositoryList = emptyList<GithubData>()

    var githubFollowerList = mutableListOf<GithubFollowerResponse>()

    init {
        //getFollowerList() // api 요청 너무 많이 해서 일단 뺐음
    }

    private fun getFollowerList() {
        viewModelScope.launch {
            githubFollowerList.addAll(githubApi.getFollowers())
            val githubList = mutableListOf<GithubData>()
            val githubUserFollowerResponse = mutableListOf<GithubUserFollowerResponse>()
            githubFollowerList.forEach {
                val userFollowerData = githubApi.getUserFollowers(it.id)
                githubUserFollowerResponse.add(userFollowerData)
            }
            githubFollowerList.forEachIndexed { index, data ->
                val newItem = GithubData(
                    name = data.id,
                    description = "follower: ${githubUserFollowerResponse[index].followers} / following: ${githubUserFollowerResponse[index].following}",
                    imageSrc = data.profileImageUrl,
                    isImageVisible = true
                )
                githubList.add(newItem)
            }
            _followerList.value = githubList
        }
    }


}