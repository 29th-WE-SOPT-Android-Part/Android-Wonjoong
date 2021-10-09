package com.wonjoong.android.sopthub.ui.home

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object ImageDataBindingAdapter {

    @BindingAdapter("bind")
    @JvmStatic
    fun setImageViewResource(imageView: ImageView, resource: Int) {
        imageView.setImageResource(resource)
    }
}