package kr.wonjoong.data.model

data class GithubData(
    val name: String,
    val description: String,
    val imageSrc: String?,
    val isImageVisible: Boolean = false,
)