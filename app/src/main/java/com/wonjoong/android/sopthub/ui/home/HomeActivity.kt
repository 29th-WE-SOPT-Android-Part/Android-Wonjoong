package com.wonjoong.android.sopthub.ui.home

import android.content.Intent
import android.net.Uri
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
        initGithubButton()
    }

    private fun initViewModel() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun initGithubButton() {
        binding.ivGithub.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/WonJoongLee"))
            startActivity(intent)
        }
    }
}