package com.strugglelin.im.contract

interface SplashContract{

    interface Presenter:BasePresenter{
        fun checkLoginStatus() // 检查登入状态
    }

    interface View{
        fun onNotLoggedIn() // 没有登入的ui处理
        fun onLoggedIn() // 已经登入的ui处理
    }
}