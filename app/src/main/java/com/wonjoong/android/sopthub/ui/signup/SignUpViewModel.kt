package com.wonjoong.android.sopthub.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.wonjoong.data.api.SignUpRequestData
import kr.wonjoong.data.api.SoptApi
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val soptApi: SoptApi
) : ViewModel() {
    private val _name = MutableLiveData("")
    val name: LiveData<String> get() = _name
    private val _isRegisterSuccess = MutableLiveData<Boolean>()
    val isRegisterSuccess: LiveData<Boolean> get() = _isRegisterSuccess

    fun signUp(email: String, name: String, password: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                soptApi.signUp(SignUpRequestData(email, name, password))
            }.onSuccess {
                _isRegisterSuccess.value = true
            }.onFailure {
                _isRegisterSuccess.value = false
            }
        }
    }
}