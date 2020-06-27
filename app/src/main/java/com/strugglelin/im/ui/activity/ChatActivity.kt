package com.strugglelin.im.ui.activity

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.strugglelin.im.R
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.header.*

/**
 *  @author strugglelin
 *  @date 2020/6/27
 *  description:
 */
class ChatActivity:BaseActivity(){

    override fun getLayoutResId(): Int = R.layout.activity_chat

    lateinit var username: String

    override fun init() {
        initHeader()
        initEditText()
    }

    private fun initHeader() {
        back.visibility = View.VISIBLE
        back.setOnClickListener{finish()}

        //获取聊天的用户名
        username = intent.getStringExtra("userName")
        val titleString = String.format(getString(R.string.chat_title), username)
        headerTitle.text = titleString
    }

    private fun initEditText() {
        edit.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {
                //如果用户输入的文本长度大于0，发送按钮enable
                send.isEnabled = !p0.isNullOrEmpty()||p0.toString().trim().length==0
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })

        edit.setOnEditorActionListener { p0, p1, p2 ->
            // send()
            true
        }
    }
}