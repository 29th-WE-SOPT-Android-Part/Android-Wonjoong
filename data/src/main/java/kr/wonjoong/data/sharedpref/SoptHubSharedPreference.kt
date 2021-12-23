package kr.wonjoong.data.sharedpref

import android.content.Context
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SoptHubSharedPreference @Inject constructor(@ApplicationContext context: Context) {
    private val preference = PreferenceManager.getDefaultSharedPreferences(context)

    fun setAutoLogin(isOn: Boolean) {
        preference.edit().putBoolean(IS_AUTO_LOGIN, isOn).apply()
    }

    fun getAutoLogin(): Boolean {
        return preference.getBoolean(IS_AUTO_LOGIN, false)
    }

    fun setOnBoardingState(state: Boolean) {
        preference.edit().putBoolean(IS_ONBOARDING_DONE, state).apply()
    }

    fun getOnBoardingState(): Boolean {
        return preference.getBoolean(IS_ONBOARDING_DONE, false)
    }

    companion object {
        private const val IS_AUTO_LOGIN = "IS_AUTO_LOGIN"
        private const val IS_ONBOARDING_DONE = "IS_ONBOARDING_DONE"
    }
}