package com.wonjoong.android.sopthub.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.databinding.FragmentOnboarding3Binding
import com.wonjoong.android.sopthub.ui.signin.SignInActivity
import com.wonjoong.android.sopthub.util.BaseViewUtil

class Onboarding3Fragment :
    BaseViewUtil.BaseFragment<FragmentOnboarding3Binding>(R.layout.fragment_onboarding_3) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNext.setOnClickListener {
            val intent = Intent(requireContext(), SignInActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }
}