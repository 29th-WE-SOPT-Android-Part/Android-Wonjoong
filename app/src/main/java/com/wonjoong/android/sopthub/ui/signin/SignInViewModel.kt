package com.wonjoong.android.sopthub.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.wonjoong.data.api.SignInRequestData
import kr.wonjoong.data.api.SoptApi
import kr.wonjoong.data.source.remote.NetworkType
import kr.wonjoong.data.source.remote.RetrofitModule

class SignInViewModel : ViewModel() {
    private val _isSignInSuccess = MutableLiveData<Boolean>()
    val isSignInSuccess: LiveData<Boolean> get() = _isSignInSuccess

    fun signIn(email: String, password: String) {
        val soptApi = RetrofitModule().createApi(SoptApi::class, NetworkType.SOPT)
        viewModelScope.launch {
            kotlin.runCatching {
                soptApi.signIn(SignInRequestData(email, password))
            }.onSuccess {
                _isSignInSuccess.value = true
            }.onFailure {
                _isSignInSuccess.value = false
            }
        }
    }
}