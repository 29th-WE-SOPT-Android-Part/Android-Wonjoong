package kr.wonjoong.data.source.local

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.wonjoong.data.model.AutoLoginData
import kr.wonjoong.data.source.SoptDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SoptRepository @Inject constructor(
    private val soptDAO: SoptDAO
) : SoptDataSource {

    override suspend fun initAutoLoginData() = withContext(Dispatchers.IO) {
        soptDAO.insertAutoLoginData(AutoLoginData(false))
    }

    override suspend fun setAutoLogin(isOn: Boolean) {
        soptDAO.updateAutoLoginData(isOn)
    }

    override suspend fun getAutoLogin(): Boolean {
        return soptDAO.getAutoLoginData().isAutoLoginState
    }
}