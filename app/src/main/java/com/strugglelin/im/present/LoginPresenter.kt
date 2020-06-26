package com.strugglelin.im.present

import com.hyphenate.chat.EMClient
import com.strugglelin.fanxinlibrary.adapter.EMCallBackAdapter
import com.strugglelin.im.contract.LoginContract
import com.strugglelin.im.extentions.isVaildPassWord
import com.strugglelin.im.extentions.isValidUserName

class LoginPresenter(val view:LoginContract.View):LoginContract.Presenter{

    override fun login(userName: String, passWord: String) {
        if(userName.isValidUserName()){
            // 用户名合法
            if (passWord.isVaildPassWord()){
                // 密码合法，开始登入
                view.onStartLogin()
                logInEaseMod(userName,passWord)
            }else{
                view.onPasswordError()
            }
        }else{
            view.onUserNameError()
        }
    }

    fun logInEaseMod(userName:String,passWord: String){

        EMClient.getInstance().login(userName,passWord,object:EMCallBackAdapter(){

            // 子线成回调
            override fun onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                // 在主线程通知view
                uiThread {
                    view.onLoggedInInSuccess()
                }
            }

            override fun onError(p0: Int, p1: String?) {
                uiThread {
                    view.onLoggedInFailed()
                }
            }

        })
    }
}