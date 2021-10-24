package kr.wonjoong.data.model

data class GithubData(
    val name: String,
    val description: String,
//    @DrawableRes
//    val imageSrc: Int,
    val isImageVisible: Boolean = false,
)