package com.wonjoong.android.sopthub.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.wonjoong.data.sharedpref.SoptHubSharedPreference
import kr.wonjoong.data.source.local.SoptRepository
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val sharedPref: SoptHubSharedPreference,
    private val repository: SoptRepository
) : ViewModel() {

    fun isOnboardingDone(): Boolean {
        return sharedPref.getOnBoardingState()
    }

    fun setOnboardingDone() {
        sharedPref.setOnBoardingState(true)
    }

    fun initAutoLoginData() {
        viewModelScope.launch {
            repository.initAutoLoginData()
        }
    }
}