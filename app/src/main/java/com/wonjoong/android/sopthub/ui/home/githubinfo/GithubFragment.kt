package com.wonjoong.android.sopthub.ui.home.githubinfo

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.databinding.FragmentGithubInfoBinding
import com.wonjoong.android.sopthub.ui.home.HomeActivity.Companion.FOLLOWER
import com.wonjoong.android.sopthub.ui.home.HomeActivity.Companion.FRAGMENT_TYPE
import com.wonjoong.android.sopthub.ui.home.HomeActivity.Companion.REPOSITORY
import com.wonjoong.android.sopthub.util.BaseViewUtil

class GithubFragment :
    BaseViewUtil.BaseFragment<FragmentGithubInfoBinding>(R.layout.fragment_github_info) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val fragmentType = arguments?.getString(FRAGMENT_TYPE)
        when (fragmentType) {
            FOLLOWER -> binding.root.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.sopt_pink
                )
            )
            REPOSITORY -> binding.root.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.sopt_gray
                )
            )
        }
    }
}