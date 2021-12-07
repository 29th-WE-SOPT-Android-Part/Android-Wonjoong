package com.wonjoong.android.sopthub.ui.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.wonjoong.data.sharedpref.SoptHubSharedPreference
import kr.wonjoong.data.source.local.SoptRepository
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    //private val sharedPreference: SoptHubSharedPreference
    private val repository: SoptRepository
) : ViewModel() {

    private val _isSwitchChecked = MutableStateFlow(false)
    val isSwitchChecked = _isSwitchChecked.asStateFlow()
    private var isAutoLogin = false

    init {
        viewModelScope.launch {
            isAutoLogin = repository.getAutoLogin()
            _isSwitchChecked.value = isAutoLogin
        }
    }

    fun setSwitchChecked() {
        _isSwitchChecked.value = !(_isSwitchChecked.value)
        viewModelScope.launch {
            repository.setAutoLogin(_isSwitchChecked.value)
        }
    }
}