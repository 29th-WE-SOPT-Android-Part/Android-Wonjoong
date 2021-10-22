package com.wonjoong.android.sopthub.ui.home.githubinfo.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.databinding.ItemGithubBinding
import com.wonjoong.android.sopthub.util.binding
import kr.wonjoong.data.model.GithubData

class GithubAdapter(
    private val onClick: ((String, String) -> Unit)?
) : ListAdapter<GithubData, GithubAdapter.GithubViewHolder>(DIFF_CALLBACK) {
    private val itemList = mutableListOf<GithubData>()
    private lateinit var cachedItem: Pair<Int, GithubData> // position과 item data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        return GithubViewHolder(parent.binding(R.layout.item_github))
    }

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setItemList(newItemList: List<GithubData>) {
        itemList.clear()
        itemList.addAll(newItemList)
    }

    fun removeItemAt(position: Int) {
        itemList.removeAt(position)
    }

    fun setCachedItem(newCachedItemPosition: Int) {
        cachedItem = Pair(newCachedItemPosition, itemList[newCachedItemPosition])
    }

    fun revertRemovedCacheItem() {
        itemList.add(cachedItem.first, cachedItem.second)
    }

    inner class GithubViewHolder(
        private val binding: ItemGithubBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            if (onClick != null) {
                binding.root.setOnClickListener {
                    val currentItem = itemList[adapterPosition]
                    onClick.invoke(currentItem.name, currentItem.description)
                }
            }
        }

        fun bind(githubData: GithubData) {
            binding.data = githubData
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GithubData>() {
            override fun areItemsTheSame(oldItem: GithubData, newItem: GithubData): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: GithubData, newItem: GithubData): Boolean {
                return oldItem == newItem
            }
        }
    }
}