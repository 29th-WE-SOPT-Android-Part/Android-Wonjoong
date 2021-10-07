package com.wonjoong.android.sopthub.ui.signin

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.databinding.ActivitySignInBinding
import com.wonjoong.android.sopthub.ui.home.HomeActivity
import com.wonjoong.android.sopthub.ui.signup.SignUpActivity
import com.wonjoong.android.sopthub.util.BaseViewUtil
import com.wonjoong.android.sopthub.util.toast

class SignInActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {

    private val TAG = "SIGN_IN_ACTIVITY"
    private lateinit var viewModel: SignInViewModel
    private lateinit var getResultText: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        setSignUpActivityResult()
        initRegisterBtn()
        initLoginBtn()
    }

    private fun setSignUpActivityResult() {
        getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val id = result.data?.getStringExtra(NAME_INTENT_KEY)
                val password = result.data?.getStringExtra(PASSWORD_INTENT_KEY)
                binding.etId.setText(id ?: "")
                binding.etPassword.setText(password ?: "")
            }
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun initRegisterBtn() {
        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            getResultText.launch(intent)
        }
    }

    private fun initLoginBtn() = with(binding) {
        btnLogin.setOnClickListener {
            if (etId.getText().isNotEmpty() && etPassword.getText().isNotEmpty()) {
                toast(String.format(resources.getString(R.string.welcome_id), etId.getText()))
                val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                toast(getString(R.string.login_fail))
            }
        }
    }

    companion object {
        const val NAME_INTENT_KEY = "name"
        const val PASSWORD_INTENT_KEY = "pw"
    }
}