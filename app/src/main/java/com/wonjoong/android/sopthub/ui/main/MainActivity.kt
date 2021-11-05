package com.wonjoong.android.sopthub.ui.main

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.databinding.ActivityMainBinding
import com.wonjoong.android.sopthub.util.BaseViewUtil

class MainActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNavigation()
    }

    private fun setNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fcv_main) as NavHostFragment
        val navController = navHostFragment.findNavController()
        binding.bnvMain.setupWithNavController(navController)
        binding.bnvMain.isItemHorizontalTranslationEnabled = true
    }
}