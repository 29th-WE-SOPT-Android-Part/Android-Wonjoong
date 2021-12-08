package com.wonjoong.android.sopthub.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.wonjoong.data.api.SignInRequestData
import kr.wonjoong.data.api.SoptApi
import kr.wonjoong.data.source.local.SoptRepository
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val soptApi: SoptApi,
    //private val sharedPreference: SoptHubSharedPreference,
    private val repository: SoptRepository
) : ViewModel() {
    private val _isSignInSuccess = MutableLiveData<Boolean>()
    val isSignInSuccess: LiveData<Boolean> get() = _isSignInSuccess

    init {
        isAutoLogin()
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                soptApi.signIn(SignInRequestData(email, password))
            }.onSuccess {
                _isSignInSuccess.postValue(true)
            }.onFailure {
                _isSignInSuccess.postValue(false)
            }
        }
    }

    private fun isAutoLogin() {
        viewModelScope.launch {
            if (repository.getAutoLogin()) _isSignInSuccess.value = true
        }
    }
}