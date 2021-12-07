package com.wonjoong.android.sopthub.util

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar

fun Context.toast(toastMessage: String) {
    Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
}

fun Context.loge(message: String) {
    Log.e(this::class.java.name, message)
}

fun Context.logi(message: String) {
    Log.i(this::class.java.name, message)
}

fun Context.logd(message: String) {
    Log.d(this::class.java.name, message)
}

fun <T> MutableLiveData<T>.notifyObserver() {
    this.postValue(this.value)
}

fun View.showSnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}