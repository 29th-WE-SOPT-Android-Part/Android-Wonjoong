package com.wonjoong.android.sopthub.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.wonjoong.data.api.SoptApi
import kr.wonjoong.data.source.remote.NetworkType
import kr.wonjoong.data.source.remote.RetrofitModule

class SignUpViewModel : ViewModel() {
    private val _name = MutableLiveData("")
    val name: LiveData<String> get() = _name

    fun singUp() {
        val soptApi = RetrofitModule().createApi(SoptApi::class, NetworkType.SOPT)
        //soptApi.signIn()
    }
}