package com.wonjoong.android.sopthub.ui.home

import androidx.lifecycle.ViewModel
import com.wonjoong.android.sopthub.R

class HomeViewModel : ViewModel() {

    val name = "이원중"
    val age = "25"
    val mbti = "SOPT"
    val introduce = "안녕하세요 솝트 안드로이드 파트 OB 이원중입니다. 솝트 29기 화이팅 ".repeat(100)
    val profileImage = R.drawable.wonjoong

}