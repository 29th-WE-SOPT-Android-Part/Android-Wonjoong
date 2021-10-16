package com.wonjoong.android.sopthub.ui.home.githubinfo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.databinding.ItemRepositoryInfoBinding
import com.wonjoong.android.sopthub.ui.home.githubinfo.data.RepositoryData

// TODO FollowerAdapter랑 하나로 합쳐도 될듯
class RepositoryAdapter(
    private val onClick: (String, String) -> Unit
) : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    private val itemList = mutableListOf<RepositoryData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemRepositoryInfoBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_repository_info, parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setItemList(newItemList: List<RepositoryData>) {
        itemList.clear()
        itemList.addAll(newItemList)
        notifyDataSetChanged()
    }

    inner class RepositoryViewHolder(
        private val binding: ItemRepositoryInfoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onClick.invoke(
                    itemList[adapterPosition].name,
                    itemList[adapterPosition].description
                )
            }
        }

        fun bind(repositoryData: RepositoryData) {
            binding.data = repositoryData
        }
    }
}
