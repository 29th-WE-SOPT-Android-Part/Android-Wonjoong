package com.wonjoong.android.sopthub.ui.main.camera

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.databinding.FragmentCameraBinding
import com.wonjoong.android.sopthub.util.BaseViewUtil
import java.util.jar.Manifest

class CameraFragment : BaseViewUtil.BaseFragment<FragmentCameraBinding>(R.layout.fragment_camera) {
    private lateinit var getImageResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initResultImageIntent()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCameraButton()
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                1
            )
        }
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
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            binding.btnCamera.setOnClickListener {
                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                getImageResult.launch(intent)
            }
        }
    }
}