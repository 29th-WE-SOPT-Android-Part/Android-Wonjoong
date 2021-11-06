package com.wonjoong.android.sopthub.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wonjoong.android.sopthub.ui.main.camera.CameraFragment
import com.wonjoong.android.sopthub.ui.main.home.HomeFragment
import com.wonjoong.android.sopthub.ui.main.profile.ProfileFragment

class MainViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ProfileFragment()
            1 -> HomeFragment()
            else -> CameraFragment()
        }
    }
}