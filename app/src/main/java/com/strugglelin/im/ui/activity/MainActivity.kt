package com.strugglelin.im.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.strugglelin.im.R
import com.strugglelin.im.factory.FragmentFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun init() {
        bottomBar.setOnTabSelectListener {
            tabId ->
            val beginTransaction = supportFragmentManager.beginTransaction()
            beginTransaction.replace(R.id.fragment_frame, FragmentFactory.instance.getFragment(tabId)!!)
            beginTransaction.commit()
        }
    }

    override fun getLayoutResId(): Int = R.layout.activity_main

}
