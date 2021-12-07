package com.wonjoong.android.sopthub.ui.onboarding

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.wonjoong.data.sharedpref.SoptHubSharedPreference
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val sharedPref: SoptHubSharedPreference
) : ViewModel() {
    fun isOnboardingDone(): Boolean {
        return sharedPref.getOnBoardingState()
    }

    fun setOnboardingDone() {
        sharedPref.setOnBoardingState(true)
    }
}