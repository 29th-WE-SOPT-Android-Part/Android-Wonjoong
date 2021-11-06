package com.wonjoong.android.sopthub.ui.main.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wonjoong.android.sopthub.ui.home.GithubFragmentType

class ProfileViewModel : ViewModel() {

    private val _name = MutableLiveData<String>("WonJoong Lee")
    val name: LiveData<String> get() = _name
    private val _id = MutableLiveData<String>("WonJoongLee")
    val id: LiveData<String> get() = _id
    private val _description = MutableLiveData<String>("안녕하세요")
    val description: LiveData<String> get() = _description
    private val _selectedFragment = MutableLiveData<GithubFragmentType>()
    val selectedFragment: LiveData<GithubFragmentType> get() = _selectedFragment

    fun setSelectedFragmentAsFollower() {
        Log.e("here2222", "hereasas")
        _selectedFragment.value = GithubFragmentType.FOLLOWER
    }

    fun setSelectedFragmentAsRepository() {
        Log.e("here33333", "hereasas")
        _selectedFragment.value = GithubFragmentType.REPOSITORY
    }
}