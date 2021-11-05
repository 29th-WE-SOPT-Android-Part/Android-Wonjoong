package com.wonjoong.android.sopthub.ui.main.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.databinding.FragmentProfileBinding
import com.wonjoong.android.sopthub.ui.home.GithubFragmentType
import com.wonjoong.android.sopthub.ui.home.githubinfo.GithubFragment
import com.wonjoong.android.sopthub.util.BaseViewUtil

class ProfileFragment :
    BaseViewUtil.BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    private val viewModel: ProfileViewModel by viewModels()
    private val followerFragment = GithubFragment(GithubFragmentType.FOLLOWER)
    private val repositoryFragment = GithubFragment(GithubFragmentType.REPOSITORY)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewModel()
        setProfileImage()
        setDefaultFragment()
        observeSelectedFragmentValue()
    }

    private fun initViewModel() {
        binding.viewModel = viewModel
    }

    private fun setProfileImage() = with(binding) {
        Glide.with(ivProfileImage)
            .load(R.drawable.wonjoong)
            .circleCrop()
            .into(ivProfileImage)
    }

    private fun setDefaultFragment() {
        setFragmentWith(followerFragment)
    }

    private fun observeSelectedFragmentValue() {
        viewModel.selectedFragment.observe(this) { newSelectedFragmentType ->
            when (newSelectedFragmentType ?: return@observe) {
                GithubFragmentType.FOLLOWER -> setFragmentWith(followerFragment)
                GithubFragmentType.REPOSITORY -> setFragmentWith(repositoryFragment)
            }
        }
    }

    private fun setFragmentWith(fragment: GithubFragment) {
        requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_github, fragment)
            .commit()
    }
}