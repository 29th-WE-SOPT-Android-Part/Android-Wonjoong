package com.wonjoong.android.sopthub.ui.home

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.databinding.ActivityHomeBinding
import com.wonjoong.android.sopthub.util.BaseViewUtil

class HomeActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityHomeBinding>(R.layout.activity_home) {

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}