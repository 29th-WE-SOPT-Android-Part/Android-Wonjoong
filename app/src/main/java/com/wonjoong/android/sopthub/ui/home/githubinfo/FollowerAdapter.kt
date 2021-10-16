package com.wonjoong.android.sopthub.ui.home.githubinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.databinding.ItemFollowerInfoBinding

class FollowerAdapter(
    private val onClick: (String, String) -> Unit
) : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {

    private val itemList = mutableListOf<FollowerData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemFollowerInfoBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_follower_info, parent, false)
        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setItemList(newItemList: List<FollowerData>) {
        itemList.clear()
        itemList.addAll(newItemList)
        notifyDataSetChanged()
    }

    inner class FollowerViewHolder(
        private val binding: ItemFollowerInfoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClick.invoke(
                    itemList[adapterPosition].name,
                    itemList[adapterPosition].description
                )
            }
        }

        fun bind(followerData: FollowerData) {
            binding.data = followerData
        }
    }
}