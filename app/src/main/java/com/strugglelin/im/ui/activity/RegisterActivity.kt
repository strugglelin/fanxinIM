package com.strugglelin.im.ui.activity

import android.view.KeyEvent
import android.widget.TextView
import com.strugglelin.im.R
import com.strugglelin.im.contract.RegisterContract
import com.strugglelin.im.present.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseActivity(), RegisterContract.View {

    val presenter = RegisterPresenter(this)

    override fun init() {
        register.setOnClickListener{
            register()
        }

        confirmPassword.setOnEditorActionListener(object: TextView.OnEditorActionListener{
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                register()
                return true
            }
        })
    }

    fun register(){
        //隐藏软键盘
        hideSoftKeyBoard()
        val userNameString = userName.text.trim().toString()
        val passwordString = password.text.trim().toString()
        val confirmPasswordString = confirmPassword.text.trim().toString()
        presenter.register(userNameString, passwordString, confirmPasswordString)
    }

    override fun onUserNameError() {
        userName.error = getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
        password.error = getString(R.string.password_error)
    }

    override fun onConfirmPasswordError() {
        confirmPassword.error = getString(R.string.confirm_password_error)
    }

    override fun onStartRegister() {
        showProgress(getString(R.string.registering))
    }

    override fun onRegisterSuccess() {
        dismissProgress()
        toast(R.string.register_success)
        finish()
    }

    override fun onRegisterFailed() {
        dismissProgress()
        toast(R.string.register_failed)
    }

    override fun onUserExist() {
        dismissProgress()
        toast(R.string.user_already_exist)
    }
    override fun getLayoutResId(): Int = R.layout.activity_register
}