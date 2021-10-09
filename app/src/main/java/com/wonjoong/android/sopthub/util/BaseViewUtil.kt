package com.wonjoong.android.sopthub.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

sealed class BaseViewUtil {
    open class BaseAppCompatActivity<T : ViewDataBinding>(@LayoutRes val layoutRes: Int) :
        AppCompatActivity() {
        lateinit var binding: T
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = DataBindingUtil.setContentView(this, layoutRes)
            binding.onCreate()
        }

        open fun T.onCreate() = Unit
    }

    open class BaseFragment<T : ViewDataBinding>(@LayoutRes val layoutRes: Int) : Fragment() {

        lateinit var binding: T

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
            binding.onCreateView()
            binding.onViewCreated()
            return binding.root
        }

        open fun T.onCreateView() = Unit
        open fun T.onViewCreated() = Unit
    }
}
