package com.wonjoong.android.sopthub.util

import android.content.Context
import android.widget.Toast

fun Context.toast(toastMessage: String) {
    Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
}