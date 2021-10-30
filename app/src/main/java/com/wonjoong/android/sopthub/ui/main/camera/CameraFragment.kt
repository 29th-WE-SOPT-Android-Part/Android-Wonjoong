package com.wonjoong.android.sopthub.ui.main.camera

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.databinding.FragmentCameraBinding
import com.wonjoong.android.sopthub.util.BaseViewUtil

class CameraFragment : BaseViewUtil.BaseFragment<FragmentCameraBinding>(R.layout.fragment_camera) {
    private lateinit var getImageResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initResultImageIntent()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCameraButton()
    }

    private fun initResultImageIntent() {
        getImageResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val uri = result.data?.data
                Glide
                    .with(binding.ivGalleryImage)
                    .load(uri)
                    .into(binding.ivGalleryImage)
            }
        }
    }

    private fun initCameraButton() {
        binding.btnCamera.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            getImageResult.launch(intent)
        }
    }
}