package com.strugglelin.fanxinlibrary

import android.app.Application
import com.hyphenate.EMCallBack
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions

object IMCore {

    val APP_KEY = "1121200625046725#fanxinim"

    fun init(applicationContext: Application) {
        val options = EMOptions()
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.acceptInvitationAlways = true
        // 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
        options.autoTransferMessageAttachments = true
        // 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
        options.setAutoDownloadThumbnail(true)
        options.setAppKey(APP_KEY)
        //初始化
        EMClient.getInstance().init(applicationContext, options)
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG)
    }

    fun isLoggedIn(): Boolean = EMClient.getInstance().isConnected && EMClient.getInstance().isLoggedInBefore

    fun login(userName:String,passWord:String,callBack: EMCallBack){
        EMClient.getInstance().login(userName,passWord,callBack)
    }
}
