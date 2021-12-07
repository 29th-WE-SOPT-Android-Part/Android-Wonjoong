package com.wonjoong.android.sopthub.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.databinding.FragmentOnboarding2Binding
import com.wonjoong.android.sopthub.util.BaseViewUtil

class Onboarding2Fragment: BaseViewUtil.BaseFragment<FragmentOnboarding2Binding>(R.layout.fragment_onboarding_2) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNext.setOnClickListener {
            findNavController().navigate(Onboarding2FragmentDirections.actionOnboarding2FragmentToOnboarding3Fragment())
        }
    }
}