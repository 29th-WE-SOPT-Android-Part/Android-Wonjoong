package com.wonjoong.android.sopthub.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.databinding.FragmentOnboarding1Binding
import com.wonjoong.android.sopthub.util.BaseViewUtil

class Onboarding1Fragment :
    BaseViewUtil.BaseFragment<FragmentOnboarding1Binding>(R.layout.fragment_onboarding_1) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNext.setOnClickListener {
            findNavController().navigate(Onboarding1FragmentDirections.actionOnboarding1FragmentToOnboarding2Fragment())
        }
    }
}
