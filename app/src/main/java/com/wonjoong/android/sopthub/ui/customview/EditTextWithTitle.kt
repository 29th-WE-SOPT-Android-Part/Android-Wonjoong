package com.wonjoong.android.sopthub.ui.customview

import android.content.Context
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.wonjoong.android.sopthub.R
import com.wonjoong.android.sopthub.databinding.EditTextWithTitleBinding

class EditTextWithTitle @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: EditTextWithTitleBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.edit_text_with_title,
        this,
        true
    )

    @get:JvmName("text")
    @set:JvmName("text")
    var text: String
        get() = getText()
        set(value) = setText(value)

    init {
        attrs?.let {
            context.obtainStyledAttributes(attrs, R.styleable.EditTextWithTitle).apply {
                val title = getString(R.styleable.EditTextWithTitle_title)
                val hint = getString(R.styleable.EditTextWithTitle_hint)
                val isPassword = getBoolean(R.styleable.EditTextWithTitle_isPassword, false)
                with(binding) {
                    tvTitle.text = title
                    etUserInput.hint = hint
                    if (isPassword) etUserInput.transformationMethod =
                        PasswordTransformationMethod.getInstance()
                }
                //recycle()
            }
        }
    }

    private fun getText() = binding.etUserInput.text.toString()
    private fun setText(text: String) {
        binding.etUserInput.setText(text)
    }

    fun isNotEmpty() = getText().isNotEmpty()
}