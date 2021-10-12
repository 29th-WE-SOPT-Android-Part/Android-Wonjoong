package com.wonjoong.android.sopthub.ui.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.databinding.ActivitySignUpBinding
import com.wonjoong.android.sopthub.ui.signin.SignInActivity
import com.wonjoong.android.sopthub.ui.signin.SignInActivity.Companion.NAME_INTENT_KEY
import com.wonjoong.android.sopthub.ui.signin.SignInActivity.Companion.PASSWORD_INTENT_KEY
import com.wonjoong.android.sopthub.ui.signin.SignInViewModel
import com.wonjoong.android.sopthub.util.BaseViewUtil
import com.wonjoong.android.sopthub.util.toast

class SignUpActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private val viewModel: SignUpViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initRootClickEvent()
        initDoneButton()
    }

    private fun initViewModel() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun initRootClickEvent() {
        binding.clRoot.setOnClickListener {
            ViewCompat.getWindowInsetsController(it)?.hide(WindowInsetsCompat.Type.ime())
        }
    }

    private fun initDoneButton() {
        binding.btnDoneRegister.setOnClickListener {
            if (isAllEditTextNotEmpty()) {
                val intent = Intent(this@SignUpActivity, SignInActivity::class.java).apply {
                    putExtra(NAME_INTENT_KEY, binding.etId.getText())
                    putExtra(PASSWORD_INTENT_KEY, binding.etPassword.getText())
                }
                setResult(RESULT_OK, intent)
                finish()
            } else {
                toast(getString(R.string.not_all_filled))
            }
        }
    }

    private fun isAllEditTextNotEmpty() = with(binding) {
        etId.isNotEmpty() && etName.isNotEmpty() && etPassword.isNotEmpty()
    }
}