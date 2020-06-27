package com.strugglelin.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.hyphenate.chat.EMClient
import com.strugglelin.fanxinlibrary.adapter.EMCallBackAdapter
import com.strugglelin.im.R
import com.strugglelin.im.bean.Friend
import kotlinx.android.synthetic.main.view_add_friend_item.view.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast

/**
 *  @author strugglelin
 *  @date 2020/6/27
 *  description:
 */
class AddFriendListItemView(context: Context, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.view_add_friend_item, this)
    }

    fun bindView(friend: Friend) {
        userName.text = friend.userName
        timestamp.text = friend.timestamp
        if (friend.isAdd) {
            add.isEnabled = false
            add.text = context.getString(R.string.already_added)
        } else {
            add.isEnabled = true
            add.text = context.getString(R.string.add)
        }

        add.setOnClickListener {
            addFriend(friend.userName)
        }
    }

    private fun addFriend(userName: String) {
        EMClient.getInstance().contactManager().aysncAddContact(userName, null, object : EMCallBackAdapter() {
            override fun onSuccess() {
                context.runOnUiThread { toast(R.string.send_add_friend_success) }
            }

            override fun onError(p0: Int, p1: String?) {
                context.runOnUiThread { toast(R.string.send_add_friend_failed) }
            }
        })
    }
}
