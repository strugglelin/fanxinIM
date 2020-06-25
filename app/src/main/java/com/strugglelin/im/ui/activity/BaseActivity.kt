package com.strugglelin.im.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId())
        init()
    }

    // 返回一个布局资源的id
    internal abstract fun layoutResId() : Int

    // 初始化数据
    open fun init(){

    }
}
