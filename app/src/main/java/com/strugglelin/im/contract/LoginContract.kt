package com.strugglelin.im.contract

interface LoginContract{

    interface Presenter : BasePresenter{
        fun login (userName:String,passWord:String)
    }

    interface View{
        fun onUserNameError()
        fun onPasswordError()
        fun onStartLogin()
        fun onLoggedInInSuccess()
        fun onLoggedInFailed()
    }
}