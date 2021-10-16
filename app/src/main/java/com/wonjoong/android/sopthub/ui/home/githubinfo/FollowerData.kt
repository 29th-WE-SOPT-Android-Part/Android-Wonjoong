package com.wonjoong.android.sopthub.ui.home.githubinfo

import androidx.annotation.DrawableRes

data class FollowerData(
    @DrawableRes
    val imageSrc: Int,
    val name: String,
    val description: String
)