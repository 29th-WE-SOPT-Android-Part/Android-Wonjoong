package com.wonjoong.android.sopthub.ui.home.githubinfo.data

import androidx.annotation.DrawableRes
import com.wonjoong.android.sopthub.R


data class GithubData(
    val name: String,
    val description: String,
    @DrawableRes
    val imageSrc: Int = R.drawable.ic_launcher_foreground,
    val isImageVisible: Boolean = false,
)