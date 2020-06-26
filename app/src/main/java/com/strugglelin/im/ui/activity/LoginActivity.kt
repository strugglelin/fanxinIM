package com.strugglelin.im.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.strugglelin.im.R
import com.strugglelin.im.contract.LoginContract
import com.strugglelin.im.present.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : BaseActivity(), LoginContract.View {

    val presenter = LoginPresenter(this)

    override fun init() {
        login.setOnClickListener {
            login()
        }

        newUser.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                startActivity<RegisterActivity>()
            }
        })

        password.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                login()
                return true // 表示处理该事件
            }
        })
    }

    fun login() {
        // 隐藏软键盘
        hideSoftKeyBoard()
        // 登入需要写磁盘权限
        if (hasWriteExternalStrogePermission()) {
            val userName = userName.text.trim().toString()
            val passWord = password.text.trim().toString()
            presenter.login(userName, passWord)
        } else {
            applyWriteExternalStoragePermission()
        }

    }

    // 检查是否有写磁盘的权限
    private fun hasWriteExternalStrogePermission(): Boolean {
        val result = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return result == PackageManager.PERMISSION_GRANTED
    }

    // 动态申请权限
    private fun applyWriteExternalStoragePermission() {
        val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        ActivityCompat.requestPermissions(this, permissions, 0)
    }

    // 动态申请权限回调
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // 用户同意权限,开始登陆
            login()
        } else {
            toast(R.string.permission_denied)
        }
    }

    override fun onUserNameError() {
        userName.error = getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
        password.error = getString(R.string.password_error)
    }

    override fun onStartLogin() {
        // 弹出进度条
        showProgress(getString(R.string.logging))
    }

    override fun onLoggedInInSuccess() {
        // 隐藏进度条
        dismissProgress()
        // 进入主界面
        startActivity<MainActivity>()
        // 退出LoginActivity
        finish()
    }

    override fun onLoggedInFailed() {
        // 隐藏进度条
        dismissProgress()
        // 弹出toast
        toast(R.string.login_failed)
    }

    override fun layoutResId(): Int = R.layout.activity_login

}