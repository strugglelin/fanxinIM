package com.strugglelin.im.present

import com.strugglelin.fanxinlibrary.IMCore
import com.strugglelin.im.contract.SplashContract

class SplashPresenter(val view:SplashContract.View) :SplashContract.Presenter{

    override fun checkLoginStatus() {
        if(isLoggedIn()) view.onLoggedIn() else view.onNotLoggedIn()
    }

    // 是否登入环信
    private fun isLoggedIn() = IMCore.isLoggedIn()
}