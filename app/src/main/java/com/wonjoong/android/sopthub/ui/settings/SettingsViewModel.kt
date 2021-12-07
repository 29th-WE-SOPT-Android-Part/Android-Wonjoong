package com.wonjoong.android.sopthub.ui.settings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.wonjoong.data.sharedpref.SoptHubSharedPreference
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val sharedPreference: SoptHubSharedPreference
): ViewModel() {
}