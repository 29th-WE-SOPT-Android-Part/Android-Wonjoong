package com.wonjoong.android.sopthub.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object ImageDataBindingAdapter {

    @BindingAdapter("src")
    @JvmStatic
    fun ImageView.setImageViewResource(resource: Int) {
        setImageResource(resource)
    }
}