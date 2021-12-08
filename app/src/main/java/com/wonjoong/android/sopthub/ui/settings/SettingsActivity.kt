package com.wonjoong.android.sopthub.ui.settings

import android.os.Bundle
import androidx.activity.viewModels
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.databinding.ActivitySettingsBinding
import com.wonjoong.android.sopthub.util.BaseViewUtil
import com.wonjoong.android.sopthub.util.observeOnce
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivitySettingsBinding>(R.layout.activity_settings) {
    private val viewModel: SettingsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragment()
        setSwitchCheck()
    }

    private fun initFragment() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun setSwitchCheck() {
        viewModel.isSwitchChecked.observeOnce(this) { newCheckedData ->
            binding.switchAutoLogin.isChecked = newCheckedData
        }
    }
}