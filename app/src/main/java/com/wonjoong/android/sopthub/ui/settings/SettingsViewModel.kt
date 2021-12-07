package com.wonjoong.android.sopthub.ui.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.wonjoong.data.sharedpref.SoptHubSharedPreference
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val sharedPreference: SoptHubSharedPreference
) : ViewModel() {

    private val _isSwitchChecked = MutableStateFlow(sharedPreference.getAutoLogin())
    val isSwitchChecked = _isSwitchChecked.asStateFlow()

    fun setSwitchChecked() {
        _isSwitchChecked.value = !(_isSwitchChecked.value)
        sharedPreference.setAutoLogin(_isSwitchChecked.value)
    }

    // 자동 로그인 원래 설정된 값 가져오기
    fun getAutoLoginData(): Boolean {
        return sharedPreference.getAutoLogin()
    }
}