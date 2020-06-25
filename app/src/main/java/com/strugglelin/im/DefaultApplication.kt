package com.strugglelin.im

import android.app.Application
import com.strugglelin.fanxinlibrary.IMCore

class DefaultApplication :Application(){

    override fun onCreate() {
        super.onCreate()
        IMCore.init(this)
    }
}