package com.wonjoong.android.sopthub.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.wonjoong.data.api.SignInRequestData
import kr.wonjoong.data.api.SoptApi
import kr.wonjoong.data.source.remote.NetworkType
import kr.wonjoong.data.source.remote.RetrofitModule

class SignUpViewModel : ViewModel() {
    private val _name = MutableLiveData("")
    val name: LiveData<String> get() = _name
    private val _isRegisterSuccess = MutableLiveData<Boolean>()
    val isRegisterSuccess: LiveData<Boolean> get() = _isRegisterSuccess

    fun signUp(email: String, name: String, password: String) {
        val soptApi = RetrofitModule().createApi(SoptApi::class, NetworkType.SOPT)
        viewModelScope.launch {
            kotlin.runCatching {
                val data = soptApi.signIn(SignInRequestData(email, name, password))
                println("-@@@@>${data.data.id}")
            }.onSuccess {
                _isRegisterSuccess.value = true
            }.onFailure {
                _isRegisterSuccess.value = false
            }
        }
    }
}