package com.wonjoong.android.sopthub.ui.home.githubinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.wonjoong.data.source.GithubRepository

class GithubViewModelFactory(
    private val githubRepository: GithubRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GithubViewModel::class.java)) {
            return GithubViewModel(githubRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}