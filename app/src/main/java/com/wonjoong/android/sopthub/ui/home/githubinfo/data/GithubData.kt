package com.wonjoong.android.sopthub.ui.home.githubinfo.data

import androidx.annotation.DrawableRes
import com.wonjoong.android.sopthub.R

//sealed class GithubData2 {
//    abstract val viewType: GithubFragmentType
//
//    data class FollowerData(
//        @DrawableRes
//        val imageSrc: Int,
//        val name: String,
//        val description: String,
//        override val viewType: GithubFragmentType
//    ) : GithubData()
//
//    data class RepositoryData(
//        val name: String,
//        val description: String,
//        override val viewType: GithubFragmentType
//    ) : GithubData()
//}


data class GithubData(
    val name: String,
    val description: String,
    @DrawableRes
    val imageSrc: Int = R.drawable.ic_launcher_foreground,
    val isImageVisible: Boolean = false,
)