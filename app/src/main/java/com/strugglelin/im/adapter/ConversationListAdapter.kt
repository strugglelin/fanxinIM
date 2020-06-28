package com.strugglelin.im.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hyphenate.chat.EMConversation
import com.strugglelin.im.ui.activity.ChatActivity
import com.strugglelin.im.widget.ConversationListItemView
import org.jetbrains.anko.startActivity

/**
 *  @author strugglelin
 *  @date 2020/6/28
 *  description:
 */
class ConversationListAdapter(
    val context: Context,
    val conversations: MutableList<EMConversation>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ConversationListItemViewHolder(ConversationListItemView(context));
    }

    override fun getItemCount(): Int = conversations.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val conversationListItemView = holder?.itemView as ConversationListItemView
        conversationListItemView.bindView(conversations[position])
        conversationListItemView.setOnClickListener {
            context.startActivity<ChatActivity>(
                "userName" to conversations[position].conversationId()
            )
        }
    }

    class ConversationListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}