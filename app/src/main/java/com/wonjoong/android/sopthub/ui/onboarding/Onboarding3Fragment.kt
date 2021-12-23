package com.wonjoong.android.sopthub.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.databinding.FragmentOnboarding3Binding
import com.wonjoong.android.sopthub.ui.signin.SignInActivity
import com.wonjoong.android.sopthub.util.BaseViewUtil

class Onboarding3Fragment :
    BaseViewUtil.BaseFragment<FragmentOnboarding3Binding>(R.layout.fragment_onboarding_3) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButton()
        initNavigation()
        setWonJoongImage()
    }

    private fun initButton() {
        binding.btnNext.setOnClickListener {
            val intent = Intent(requireContext(), SignInActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    private fun initNavigation() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(Onboarding3FragmentDirections.actionOnboarding3FragmentToOnboarding1Fragment())
        }
    }

    private fun setWonJoongImage() {
        Glide
            .with(binding.ivWonjoongGithubImage)
            .load(R.drawable.wonjoong)
            .circleCrop()
            .into(binding.ivWonjoongGithubImage)
    }
}