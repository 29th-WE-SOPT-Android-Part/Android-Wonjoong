package com.wonjoong.android.sopthub.ui.detail

import android.os.Bundle
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.databinding.ActivityItemDetailBinding
import com.wonjoong.android.sopthub.util.BaseViewUtil

class ItemDetailActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityItemDetailBinding>(R.layout.activity_item_detail) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setIntentData()
    }

    private fun setIntentData() {
        val bundle = intent.extras
        if (bundle != null) {
            val name = bundle.getString("name") ?: ""
            val description = bundle.getString("description") ?: ""
            binding.tvName.text = name
            binding.tvDescription.text = description
        }
    }

}