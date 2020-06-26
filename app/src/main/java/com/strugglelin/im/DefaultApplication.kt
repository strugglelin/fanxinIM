package com.strugglelin.im

import android.app.Application
import com.strugglelin.bmoblibrary.BmobCore
import com.strugglelin.fanxinlibrary.IMCore

class DefaultApplication :Application(){

    override fun onCreate() {
        super.onCreate()
        IMCore.init(this)
        BmobCore.init(this)
    }
}