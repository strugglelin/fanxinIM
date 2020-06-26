package com.strugglelin.im.present

import androidx.annotation.UiThread
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import com.strugglelin.im.contract.RegisterContract
import com.strugglelin.im.extentions.isVaildPassWord
import com.strugglelin.im.extentions.isValidUserName
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class RegisterPresenter(val view: RegisterContract.View) : RegisterContract.Presenter {

    override fun register(userName: String, password: String, confirmPassword: String) {
        if (userName.isValidUserName()) {
            if (confirmPassword.isVaildPassWord()) {
                if (password.equals(confirmPassword)) {
                    // 用户名密码一致
                    view.onStartRegister()
                    // 开始注册
                    registerBmob(userName, password)
                } else view.onConfirmPasswordError()
            } else view.onPasswordError()
        } else view.onUserNameError()
    }

    private fun registerBmob(userName: String, password: String) {
        val bu = BmobUser()
        bu.username = userName
        bu.setPassword(password)
        bu.signUp(object : SaveListener<BmobUser>() {
            override fun done(p0: BmobUser?, p1: BmobException?) {
                if(p1==null){
                    // 注册bmob成功
                    // 注册环信
                    registerEaseMob(userName, password)
                }else{
                    // 注册失败
                    if(p1.errorCode == 202) view.onUserExist()
                    view.onRegisterFailed()
                }
            }
        })
    }

    private fun registerEaseMob(userName: String, password: String){
        doAsync {
            try {
                //注册失败会抛出HyphenateException
                EMClient.getInstance().createAccount(userName, password);//同步方法
                //注册成功
                uiThread { view.onRegisterSuccess() }
            } catch (e: HyphenateException) {
                //注册失败
                uiThread { view.onRegisterFailed() }
            }
        }
    }
}