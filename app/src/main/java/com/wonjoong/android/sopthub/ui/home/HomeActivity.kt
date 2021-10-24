package com.wonjoong.android.sopthub.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.databinding.ActivityHomeBinding
import com.wonjoong.android.sopthub.ui.home.githubinfo.GithubFragment
import com.wonjoong.android.sopthub.util.BaseViewUtil

class HomeActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityHomeBinding>(R.layout.activity_home) {

    private val viewModel: HomeViewModel by viewModels()
    private val followerFragment = GithubFragment()
    private val repositoryFragment = GithubFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        setFragmentArgument()
        setDefaultFragment()
        observeSelectedFragmentValue()
    }

    private fun initViewModel() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun setFragmentArgument() {
        followerFragment.arguments = Bundle().apply {
            putString(FRAGMENT_TYPE, FOLLOWER)
        }
        repositoryFragment.arguments = Bundle().apply {
            putString(FRAGMENT_TYPE, REPOSITORY)
        }
    }

    private fun setDefaultFragment() {
        setFragmentWith(followerFragment)
    }

    private fun observeSelectedFragmentValue() {
        viewModel.selectedFragment.observe(this) { newSelectedFragmentType ->
            when (newSelectedFragmentType) {
                GithubFragmentType.FOLLOWER -> setFragmentWith(followerFragment)
                GithubFragmentType.REPOSITORY -> setFragmentWith(repositoryFragment)
            }
        }
    }

    private fun setFragmentWith(fragment: GithubFragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_github, fragment)
            .commit()
    }

    companion object {
        const val FRAGMENT_TYPE = "fragment_type"
        const val FOLLOWER = "follower"
        const val REPOSITORY = "repository"
    }
}