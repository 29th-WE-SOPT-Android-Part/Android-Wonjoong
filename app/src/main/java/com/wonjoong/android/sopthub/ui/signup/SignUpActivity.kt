package com.wonjoong.android.sopthub.ui.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.databinding.ActivitySignUpBinding
import com.wonjoong.android.sopthub.ui.signin.SignInActivity
import com.wonjoong.android.sopthub.ui.signin.SignInActivity.Companion.NAME_INTENT_KEY
import com.wonjoong.android.sopthub.ui.signin.SignInActivity.Companion.PASSWORD_INTENT_KEY
import com.wonjoong.android.sopthub.util.BaseViewUtil
import com.wonjoong.android.sopthub.util.showSnackBar
import com.wonjoong.android.sopthub.util.toast

class SignUpActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private val viewModel: SignUpViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initRootClickEvent()
        initDoneButton()
        observeRegisterSuccessfullyDone()
    }

    private fun initViewModel() {
        binding.viewModel = viewModel
    }

    private fun initRootClickEvent() {
        binding.clRoot.setOnClickListener {
            ViewCompat.getWindowInsetsController(it)?.hide(WindowInsetsCompat.Type.ime())
        }
    }

    private fun initDoneButton() = with(binding) {
        btnDoneRegister.setOnClickListener {
            if (isAllEditTextNotEmpty()) {
                viewModel?.signUp(etId.text, etName.text, etPassword.text)
            } else {
                toast(getString(R.string.not_all_filled))
            }
        }
    }

    private fun isAllEditTextNotEmpty() = with(binding) {
        etId.isNotEmpty() && etName.isNotEmpty() && etPassword.isNotEmpty()
    }

    private fun observeRegisterSuccessfullyDone() {
        viewModel.isRegisterSuccess.observe(this) { newRegisterResponse ->
            if (newRegisterResponse) {
                val intent = Intent(this@SignUpActivity, SignInActivity::class.java).apply {
                    putExtra(NAME_INTENT_KEY, binding.etId.text)
                    putExtra(PASSWORD_INTENT_KEY, binding.etPassword.text)
                }
                setResult(RESULT_OK, intent)
                finish()
            } else {
                binding.root.showSnackBar("다시 한 번 확인해주세요.")
            }
        }
    }
}