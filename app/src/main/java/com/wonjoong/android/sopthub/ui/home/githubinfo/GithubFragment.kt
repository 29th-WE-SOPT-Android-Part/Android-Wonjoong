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
import com.wonjoong.android.sopthub.ui.home.githubinfo.adapter.GithubAdapter
import com.wonjoong.android.sopthub.util.BaseViewUtil
import com.wonjoong.android.sopthub.util.GithubRecyclerViewItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GithubFragment(
    private val fragmentType: GithubFragmentType
) :
    BaseViewUtil.BaseFragment<FragmentGithubInfoBinding>(R.layout.fragment_github_info) {

    private val viewModel: GithubViewModel by viewModels()
    private val followerAdapter = GithubAdapter(this::moveToPersonDetail)
    private val repositoryAdapter = GithubAdapter(null)
    private lateinit var currentAdapter: GithubAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setViewModel()
        setFragmentWithFragmentType()
        observeRecyclerViewItem()
    }

    private fun setViewModel() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun setFragmentWithFragmentType() {
        when (fragmentType) {
            GithubFragmentType.FOLLOWER -> {
                val linearlayoutManager = LinearLayoutManager(requireContext())
                setRecyclerView(linearlayoutManager, GithubFragmentType.FOLLOWER)
            }
            GithubFragmentType.REPOSITORY -> {
                val gridLayoutManager = GridLayoutManager(requireContext(), 3)
                setRecyclerView(gridLayoutManager, GithubFragmentType.REPOSITORY)
            }
        }
    }

    private fun setRecyclerView(
        layoutManager: RecyclerView.LayoutManager,
        fragmentType: GithubFragmentType
    ) {
        currentAdapter =
            if (fragmentType == GithubFragmentType.FOLLOWER) followerAdapter else repositoryAdapter
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
            adapter = currentAdapter
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
                with(currentAdapter) {
                    setCachedItem(swipedPosition)
                    removeItemAt(swipedPosition)
                    notifyItemRemoved(swipedPosition)
                }
                binding.root.showSnackBar("?????????????????????.", swipedPosition)
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
    }

    private fun View.showSnackBar(message: String, swipedPosition: Int) {
        Snackbar.make(this, message, Snackbar.LENGTH_SHORT).apply {
            setAction("????????????") {
                currentAdapter.revertRemovedCacheItem()
                currentAdapter.notifyItemInserted(swipedPosition)
                if (swipedPosition == 0) binding.rvGithubInfo.smoothScrollToPosition(0)
                dismiss()
            }
            setActionTextColor(ContextCompat.getColor(requireContext(), R.color.sopt_pink))
            show()
        }
    }
}