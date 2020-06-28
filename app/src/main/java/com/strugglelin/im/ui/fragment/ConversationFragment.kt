package com.strugglelin.im.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMConversation
import com.hyphenate.chat.EMMessage
import com.strugglelin.fanxinlibrary.adapter.EMMessageListenerAdapter
import com.strugglelin.im.R
import com.strugglelin.im.adapter.ConversationListAdapter
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 *  @author strugglelin
 *  @date 2020/6/26
 *  description:
 */

class ConversationFragment : BaseFragment(){

    override fun getLayoutResId(): Int = R.layout.fragment_conversation
    val conversations = mutableListOf<EMConversation>()
    val messageListener = object : EMMessageListenerAdapter(){
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            loadConversations()
        }
    }
    override fun init() {
        headerTitle.text = getString(R.string.message)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ConversationListAdapter(context,conversations)
        }

        EMClient.getInstance().chatManager().addMessageListener(messageListener)

//        loadConversations()
    }

    private fun loadConversations() {
        doAsync {
            //清空会话列表
            conversations.clear()
            val allConversations = EMClient.getInstance().chatManager().allConversations
            conversations.addAll(allConversations.values)
            uiThread { recyclerView.adapter?.notifyDataSetChanged() }
        }
    }

    override fun onResume() {
        super.onResume()
        loadConversations()
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
    }

}