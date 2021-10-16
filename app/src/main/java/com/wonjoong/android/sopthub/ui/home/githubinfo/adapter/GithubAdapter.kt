package com.wonjoong.android.sopthub.ui.home.githubinfo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.databinding.ItemGithubBinding
import com.wonjoong.android.sopthub.ui.home.githubinfo.data.GithubData

class GithubAdapter(
    private val onClick: ((String, String) -> Unit)?
) : RecyclerView.Adapter<GithubAdapter.GithubViewHolder>() {
    private val itemList = mutableListOf<GithubData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemGithubBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_github, parent, false)
        return GithubViewHolder(binding)
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
        notifyDataSetChanged() // TODO diff util로 개선
    }

    fun removeItemAt(position: Int) {
        itemList.removeAt(position)
        notifyDataSetChanged() // TODO diff util로 개선
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
}