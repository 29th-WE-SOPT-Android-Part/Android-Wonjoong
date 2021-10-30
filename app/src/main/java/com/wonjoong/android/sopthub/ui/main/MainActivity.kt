package com.wonjoong.android.sopthub.ui.main

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.databinding.ActivityMainBinding
import com.wonjoong.android.sopthub.ui.main.adapter.MainViewPagerAdapter
import com.wonjoong.android.sopthub.util.BaseViewUtil

class MainActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vpMain.adapter = MainViewPagerAdapter(this)
        binding.vpMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.bnvMain.menu.getItem(position).isChecked = true
            }
        })
        binding.bnvMain.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.profileFragment -> {
                    binding.vpMain.currentItem = 0
                    return@setOnItemSelectedListener true
                }
                R.id.homeFragment -> {
                    binding.vpMain.currentItem = 1
                    return@setOnItemSelectedListener true
                }
                else -> {
                    binding.vpMain.currentItem = 2
                    return@setOnItemSelectedListener true
                }
            }
        }
        //binding.bnvMain.isItemHorizontalTranslationEnabled = true
    }

    // 추후 다시 navigation graph로 변경할 예정
//    private fun setNavigation() {
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.fcv_main) as NavHostFragment
//        val navController = navHostFragment.findNavController()
//        binding.bnvMain.setupWithNavController(navController)
//        binding.bnvMain.isItemHorizontalTranslationEnabled = true
//    }
}