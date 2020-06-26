package com.strugglelin.im.ui.activity

import android.os.Handler
import com.strugglelin.im.R
import com.strugglelin.im.contract.SplashContract
import com.strugglelin.im.present.SplashPresenter
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity(), SplashContract.View {

    val presenter = SplashPresenter(this)

    companion object {
        val DELAY = 2000L
    }

    val handler by lazy {
        Handler()
    }

    override fun getLayoutResId(): Int = R.layout.activity_splash

    override fun init() {
        presenter.checkLoginStatus()
    }

    // 延迟2s调到登入界面
    override fun onNotLoggedIn() {
        handler.postDelayed({
            startActivity<LoginActivity>()
            finish()
        }, DELAY)
    }

    override fun onLoggedIn() {
        startActivity<MainActivity>()
        finish()
    }
}