package com.strugglelin.im.ui.fragment

import android.view.View
import com.hyphenate.chat.EMClient
import com.strugglelin.fanxinlibrary.adapter.EMCallBackAdapter
import com.strugglelin.im.R
import com.strugglelin.im.ui.activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_dynamic.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


/**
 *  @author strugglelin
 *  @date 2020/6/26
 *  description:
 */

class MeFragment : BaseFragment() {

    override fun getLayoutResId(): Int = R.layout.fragment_dynamic

    override fun init() {
        headerTitle.text = getString(R.string.dynamic)
        val logoutString = String.format(getString(R.string.logout), EMClient.getInstance().currentUser)
        logout.text = logoutString

        logout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                logout()
            }
        })
    }

    fun logout() {
        EMClient.getInstance().logout(true, object : EMCallBackAdapter() {

            override fun onSuccess() {
                // 切换到主线程
                context?.runOnUiThread {
                    toast(R.string.logout_success)
                    startActivity<LoginActivity>()
                    activity?.finish()
                }
            }

            override fun onError(p0: Int, p1: String?) {
                context?.runOnUiThread {
                    toast(R.string.logout_failed)
                }
            }
        })
    }
}