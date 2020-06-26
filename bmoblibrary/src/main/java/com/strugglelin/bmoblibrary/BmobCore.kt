package com.strugglelin.bmoblibrary

import android.app.Application
import cn.bmob.v3.Bmob

object BmobCore{

    val APP_KEY = "3874213e8122eb70f768060dd5775c22"

    fun init(application:Application) = Bmob.initialize(application, APP_KEY)
}