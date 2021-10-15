package com.wonjoong.android.sopthub.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wonjoong.android.sopthub.R

class HomeViewModel : ViewModel() {

    val name = "이원중"
    val age = "25"
    val mbti = "SOPT"
    val profileImage = R.drawable.wonjoong

    private val _selectedFragment = MutableLiveData<GithubFragmentType>()
    val selectedFragment: LiveData<GithubFragmentType> get() = _selectedFragment

    fun setSelectedFragmentAsFollower() {
        _selectedFragment.value = GithubFragmentType.Follower
    }

    fun setSelectedFragmentAsRepository() {
        _selectedFragment.value = GithubFragmentType.Repository
    }
}