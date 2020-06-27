package com.strugglelin.im

import android.app.Application
import com.strugglelin.bmoblibrary.BmobCore
import com.strugglelin.fanxinlibrary.IMCore

class DefaultApplication :Application(){

    companion object {
        lateinit var instance :DefaultApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        IMCore.init(this)
        BmobCore.init(this)
    }
}