package com.wonjoong.android.sopthub.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.databinding.ActivityOnboardingBinding
import com.wonjoong.android.sopthub.ui.signin.SignInActivity
import com.wonjoong.android.sopthub.util.BaseViewUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityOnboardingBinding>(R.layout.activity_onboarding) {
    private val viewModel: OnboardingViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(viewModel.isOnboardingDone()) {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            this.finish()
        }
        viewModel.setOnboardingDone()
        setNavHostFragment()
    }

    private fun setNavHostFragment() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.findNavController()
        val toolBarConfiguration = AppBarConfiguration(navController.graph)
        binding.tbTitle.setupWithNavController(navController, toolBarConfiguration)
    }
}