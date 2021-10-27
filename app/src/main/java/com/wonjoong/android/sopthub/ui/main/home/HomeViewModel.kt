package com.wonjoong.android.sopthub.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wonjoong.android.sopthub.ui.home.GithubFragmentType

class HomeViewModel : ViewModel() {
    private val _selectedFragment = MutableLiveData<GithubFragmentType>()
    val selectedFragment: LiveData<GithubFragmentType> get() = _selectedFragment

    fun setSelectedFragmentAsFollower() {
        _selectedFragment.value = GithubFragmentType.FOLLOWER
    }

    fun setSelectedFragmentAsRepository() {
        _selectedFragment.value = GithubFragmentType.REPOSITORY
    }
}