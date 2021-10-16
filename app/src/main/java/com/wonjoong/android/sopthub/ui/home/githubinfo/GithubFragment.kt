package com.wonjoong.android.sopthub.ui.home.githubinfo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.databinding.FragmentGithubInfoBinding
import com.wonjoong.android.sopthub.ui.detail.ItemDetailActivity
import com.wonjoong.android.sopthub.ui.home.HomeActivity.Companion.FOLLOWER
import com.wonjoong.android.sopthub.ui.home.HomeActivity.Companion.FRAGMENT_TYPE
import com.wonjoong.android.sopthub.ui.home.HomeActivity.Companion.REPOSITORY
import com.wonjoong.android.sopthub.ui.home.githubinfo.adapter.GithubAdapter
import com.wonjoong.android.sopthub.util.BaseViewUtil
import com.wonjoong.android.sopthub.util.toast

class GithubFragment :
    BaseViewUtil.BaseFragment<FragmentGithubInfoBinding>(R.layout.fragment_github_info) {

    private lateinit var fragmentType: String
    private val viewModel: GithubViewModel by viewModels()
    private lateinit var followerAdapter: GithubAdapter
    private lateinit var repositoryAdapter: GithubAdapter

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
        followerAdapter = GithubAdapter(this::moveToPersonDetail)
        repositoryAdapter = GithubAdapter(null) // 클릭이 되지 않도록 null을 넣어준다
        when (fragmentType) {
            FOLLOWER -> {
                val linearlayoutManager = LinearLayoutManager(requireContext())
                binding.rvGithubInfo.layoutManager = linearlayoutManager
                binding.rvGithubInfo.adapter = followerAdapter
            }
            REPOSITORY -> {
                val gridLayoutManager = GridLayoutManager(requireContext(), 3)
                binding.rvGithubInfo.layoutManager = gridLayoutManager
                binding.rvGithubInfo.adapter = repositoryAdapter
            }
        }
    }

    private fun moveToPersonDetail(name: String, description: String) {
        // TODO
        requireContext().toast("이름 : $name, 설명 : $description")
        val intent = Intent(requireContext(), ItemDetailActivity::class.java)
        val bundle = Bundle().apply {
            putString("name", name)
            putString("description", description)
        }
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun observeRecyclerViewItem() {
        viewModel.followerList.observe(viewLifecycleOwner) { newFollowerList ->
            followerAdapter.setItemList(newFollowerList)
        }
        viewModel.repositoryList.observe(viewLifecycleOwner) { newRepositoryList ->
            repositoryAdapter.setItemList(newRepositoryList)
        }
    }
}