package com.wonjoong.android.sopthub.ui.signin

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.databinding.ActivitySignInBinding
import com.wonjoong.android.sopthub.ui.main.MainActivity
import com.wonjoong.android.sopthub.ui.signup.SignUpActivity
import com.wonjoong.android.sopthub.util.BaseViewUtil
import com.wonjoong.android.sopthub.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {

    private val viewModel: SignInViewModel by viewModels()
    private lateinit var getResultText: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initRootClickEvent()
        setSignUpActivityResult()
        initRegisterBtn()
        initLoginBtn()
        observeSignInSuccessfullyDone()
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

    private fun setSignUpActivityResult() {
        getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val id = result.data?.getStringExtra(NAME_INTENT_KEY)
                val password = result.data?.getStringExtra(PASSWORD_INTENT_KEY)
                binding.etId.text = id ?: ""
                binding.etPassword.text = password ?: ""
            }
        }
    }

    private fun initRegisterBtn() {
        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            getResultText.launch(intent)
        }
    }

    private fun initLoginBtn() = with(binding) {
        btnLogin.setOnClickListener {
            if (etId.text.isNotEmpty() && etPassword.text.isNotEmpty()) {
                this@SignInActivity.viewModel.signIn(etId.text, etPassword.text)
            } else {
                toast(getString(R.string.login_fail))
            }
        }
    }

    private fun observeSignInSuccessfullyDone() {
        viewModel.isSignInSuccess.observe(this) { isSuccess ->
            val intent = Intent(this@SignInActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
//            if (isSuccess) {
//                toast(String.format(resources.getString(R.string.welcome_id), binding.etId.text))
//                val intent = Intent(this@SignInActivity, MainActivity::class.java)
//                startActivity(intent)
//                finish()
//            } else {
//                toast(getString(R.string.Sign_In_Activity_Check_ID_PW_Again))
//            }
        }
    }

    companion object {
        const val NAME_INTENT_KEY = "name"
        const val PASSWORD_INTENT_KEY = "pw"
    }
}