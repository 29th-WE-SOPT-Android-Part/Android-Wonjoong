package com.wonjoong.android.sopthub.ui.home.githubinfo

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.databinding.FragmentGithubInfoBinding
import com.wonjoong.android.sopthub.ui.home.HomeActivity.Companion.FOLLOWER
import com.wonjoong.android.sopthub.ui.home.HomeActivity.Companion.FRAGMENT_TYPE
import com.wonjoong.android.sopthub.ui.home.HomeActivity.Companion.REPOSITORY
import com.wonjoong.android.sopthub.ui.home.githubinfo.adapter.FollowerAdapter
import com.wonjoong.android.sopthub.util.BaseViewUtil
import com.wonjoong.android.sopthub.util.toast

class GithubFragment :
    BaseViewUtil.BaseFragment<FragmentGithubInfoBinding>(R.layout.fragment_github_info) {

    private lateinit var fragmentType: String
    private val viewModel: GithubViewModel by viewModels()
    private lateinit var followerAdapter: FollowerAdapter
    private lateinit var repositoryAdapter: FollowerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setViewModel()
        fragmentType = arguments?.getString(FRAGMENT_TYPE)
            ?: throw IllegalArgumentException("Please check argument again")
        setFragmentWithFragmentType()
        observeRecyclerViewItem()
    }

    private fun setViewModel() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun setFragmentWithFragmentType() {
        followerAdapter = FollowerAdapter(this::moveToPersonDetail)
        when (fragmentType) {
            FOLLOWER -> {
                val linearlayoutManager = LinearLayoutManager(requireContext())
                binding.rvGithubInfo.layoutManager = linearlayoutManager
                binding.rvGithubInfo.adapter = followerAdapter
            }
            REPOSITORY -> {
                binding.root.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.sopt_gray
                    )
                )
            }
        }
    }

    private fun moveToPersonDetail(name: String, description: String) {
        // TODO
        requireContext().toast("이름 : $name, 설명 : $description")
    }

    private fun observeRecyclerViewItem() {
        viewModel.followerList.observe(viewLifecycleOwner) { newFollowerList ->
            followerAdapter.setItemList(newFollowerList)
        }
    }

}