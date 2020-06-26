package com.strugglelin.im.ui.activity

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    val progressDialog by lazy {
        ProgressDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId())
        init()
    }

    // 返回一个布局资源的id
    abstract fun layoutResId() : Int

    // 初始化数据
    open fun init(){
    }

    fun showProgress(message:String){
        progressDialog.setMessage(message)
        progressDialog.show()
    }

    fun dissmissProgress(){
        progressDialog.dismiss()
    }
}
