package com.wonjoong.android.sopthub.ui.home.githubinfo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.databinding.FragmentGithubInfoBinding
import com.wonjoong.android.sopthub.ui.detail.ItemDetailActivity
import com.wonjoong.android.sopthub.ui.home.GithubFragmentType
import com.wonjoong.android.sopthub.ui.home.HomeActivity.Companion.FOLLOWER
import com.wonjoong.android.sopthub.ui.home.HomeActivity.Companion.FRAGMENT_TYPE
import com.wonjoong.android.sopthub.ui.home.HomeActivity.Companion.REPOSITORY
import com.wonjoong.android.sopthub.ui.home.githubinfo.adapter.GithubAdapter
import com.wonjoong.android.sopthub.util.BaseViewUtil
import com.wonjoong.android.sopthub.util.GithubRecyclerViewItemDecoration
import kr.wonjoong.data.source.GithubRepository
import kr.wonjoong.data.source.local.GithubLocalDataSource

class GithubFragment :
    BaseViewUtil.BaseFragment<FragmentGithubInfoBinding>(R.layout.fragment_github_info) {

    private lateinit var fragmentType: String
    private val githubRepository = GithubRepository(GithubLocalDataSource())
    private val viewModel: GithubViewModel by viewModels { GithubViewModelFactory(githubRepository) }
    private lateinit var followerAdapter: GithubAdapter
    private lateinit var repositoryAdapter: GithubAdapter
    private lateinit var mAdapter: GithubAdapter

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
                setRecyclerView(linearlayoutManager, GithubFragmentType.Follower)
            }
            REPOSITORY -> {
                val gridLayoutManager = GridLayoutManager(requireContext(), 3)
                setRecyclerView(gridLayoutManager, GithubFragmentType.Repository)
            }
        }
    }

    private fun setRecyclerView(
        layoutManager: RecyclerView.LayoutManager,
        fragmentType: GithubFragmentType
    ) {
        mAdapter =
            if (fragmentType == GithubFragmentType.Follower) followerAdapter else repositoryAdapter
        val itemTouchHelper = getItemSwipeHelper()
        val customItemDecoration = GithubRecyclerViewItemDecoration(
            20,
            ContextCompat.getColor(requireContext(), R.color.sopt_pink),
            4f,
            fragmentType
        )
        with(binding.rvGithubInfo) {
            addItemDecoration(customItemDecoration)
            this.layoutManager = layoutManager
            adapter = mAdapter
            itemTouchHelper.attachToRecyclerView(this)
        }
    }

    private fun getItemSwipeHelper(): ItemTouchHelper {
        val itemSwipeCallback = object : SimpleCallback(0, RIGHT or LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val swipedPosition = viewHolder.adapterPosition
                mAdapter.setCachedItem(swipedPosition)
                mAdapter.removeItemAt(swipedPosition)
                mAdapter.notifyItemRemoved(swipedPosition)
                binding.root.showSnackBar("삭제되었습니다.", swipedPosition)
            }
        }
        return ItemTouchHelper(itemSwipeCallback)
    }

    private fun moveToPersonDetail(name: String, description: String) {
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

    private fun View.showSnackBar(message: String, swipedPosition: Int) {
        Snackbar.make(this, message, Snackbar.LENGTH_SHORT).apply {
            setAction("되돌리기") {
                mAdapter.revertRemovedCacheItem()
                mAdapter.notifyItemInserted(swipedPosition)
                if (swipedPosition == 0) binding.rvGithubInfo.smoothScrollToPosition(0)
                dismiss()
            }
            setActionTextColor(ContextCompat.getColor(requireContext(), R.color.sopt_pink))
            show()
        }
    }
}