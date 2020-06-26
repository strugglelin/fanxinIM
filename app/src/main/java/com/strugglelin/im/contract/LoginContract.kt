package com.strugglelin.im.contract

class LoginContract{

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