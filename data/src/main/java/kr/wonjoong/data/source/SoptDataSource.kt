package kr.wonjoong.data.source

interface SoptDataSource {
    suspend fun initAutoLoginData()
    suspend fun setAutoLogin(isOn: Boolean)
    suspend fun getAutoLogin(): Boolean
}