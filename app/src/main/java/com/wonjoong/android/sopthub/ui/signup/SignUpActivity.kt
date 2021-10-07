package com.wonjoong.android.sopthub.ui.signup

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.databinding.ActivitySignUpBinding
import com.wonjoong.android.sopthub.ui.signin.SignInActivity
import com.wonjoong.android.sopthub.ui.signin.SignInActivity.Companion.NAME_INTENT_KEY
import com.wonjoong.android.sopthub.ui.signin.SignInActivity.Companion.PASSWORD_INTENT_KEY
import com.wonjoong.android.sopthub.util.BaseViewUtil
import com.wonjoong.android.sopthub.util.toast

class SignUpActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private lateinit var viewModel: SignUpViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initDoneButton()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
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