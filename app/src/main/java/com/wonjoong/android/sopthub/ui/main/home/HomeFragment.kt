package com.wonjoong.android.sopthub.ui.main.home

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.databinding.FragmentHomeBinding
import com.wonjoong.android.sopthub.util.BaseViewUtil

class HomeFragment : BaseViewUtil.BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initTabLayout()
    }

    private fun initAdapter() {
        binding.vpFollow.adapter = FollowerViewPagerAdapter(requireActivity())
    }

    private fun initTabLayout() {
        TabLayoutMediator(binding.tlFollow, binding.vpFollow) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "팔로잉"
                }
                else -> {
                    tab.text = "팔로워"
                }
            }
        }.attach()
    }
}