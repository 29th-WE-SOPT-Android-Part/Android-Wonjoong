package com.wonjoong.android.sopthub.util

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData

fun Context.toast(toastMessage: String) {
    Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
}

fun <T> MutableLiveData<T>.notifyObserver() {
    this.value = this.value
}