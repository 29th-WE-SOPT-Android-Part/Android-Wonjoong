package kr.wonjoong.data.source.local

import kr.wonjoong.data.model.AutoLoginData
import kr.wonjoong.data.source.SoptDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SoptRepository @Inject constructor(
    private val soptDAO: SoptDAO
) : SoptDataSource {

    override suspend fun initAutoLoginData() {
        soptDAO.insertAutoLoginData(AutoLoginData(false))
    }

    override suspend fun setAutoLogin(isOn: Boolean) {
        soptDAO.updateAutoLoginData(AutoLoginData(isOn))
    }

    override suspend fun getAutoLogin(): Boolean {
        return soptDAO.getAutoLoginData()
    }
}