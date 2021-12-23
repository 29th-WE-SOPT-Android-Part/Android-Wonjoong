package com.wonjoong.android.sopthub.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.wonjoong.data.source.local.SoptRepository
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: SoptRepository
) : ViewModel() {

    private val _isSwitchChecked = MutableLiveData<Boolean>()
    val isSwitchChecked: LiveData<Boolean> get() = _isSwitchChecked
    private var changedByDBFlag = false // DB값에 의해 켜지면 리스너가 동작하지 않도록 하기 위해 추가된 플래그

    init {
        getAutoLoginData()
    }

    fun setSwitchChecked() {
        if (changedByDBFlag) {
            changedByDBFlag = false
            return
        }
        _isSwitchChecked.value = !(_isSwitchChecked.value ?: return)
        viewModelScope.launch {
            repository.setAutoLogin(_isSwitchChecked.value ?: return@launch)
        }
    }

    private fun getAutoLoginData() {
        viewModelScope.launch {
            val isAutoLogin = repository.getAutoLogin()
            if (isAutoLogin) changedByDBFlag = true
            _isSwitchChecked.value = isAutoLogin
        }
    }
}