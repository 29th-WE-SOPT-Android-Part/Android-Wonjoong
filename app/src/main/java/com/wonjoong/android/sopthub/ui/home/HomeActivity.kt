package com.wonjoong.android.sopthub.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.databinding.ActivityHomeBinding
import com.wonjoong.android.sopthub.util.BaseViewUtil

class HomeActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityHomeBinding>(R.layout.activity_home) {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}