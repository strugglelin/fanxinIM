package com.strugglelin.im.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hyphenate.chat.EMMessage
import com.hyphenate.util.DateUtils
import com.strugglelin.im.widget.ReceiveMessageItemView
import com.strugglelin.im.widget.SendMessageItemView

/**
 *  @author strugglelin
 *  @date 2020/6/28
 *  description:
 */
class MessageListAdapter(val context:Context,val messages:List<EMMessage>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    companion object {
        val ITEM_TYPE_SEND_MESSAGE = 0
        val ITEM_TYPE_RECEIVE_MESSAGE = 1
    }

    override fun getItemViewType(position: Int): Int {
        if(messages[position].direct()==EMMessage.Direct.SEND){
            return ITEM_TYPE_SEND_MESSAGE
        }else{
            return ITEM_TYPE_RECEIVE_MESSAGE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType==ITEM_TYPE_SEND_MESSAGE){
            return SendMessageViewHolder(SendMessageItemView(context))
        }else return ReceiveMessageViewHolder(ReceiveMessageItemView(context))
    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val showTimestamp = isShowTimestamp(position)
        if(getItemViewType(position)==ITEM_TYPE_SEND_MESSAGE){
            val sendMessageItemView = holder.itemView as SendMessageItemView
            sendMessageItemView.bindView(messages[position],showTimestamp)
        }else{
            val receiveMessageItemView = holder.itemView as ReceiveMessageItemView
             receiveMessageItemView.bindView(messages[position],showTimestamp)
        }
    }

    private fun isShowTimestamp(position: Int): Boolean {
        //如果是第一条消息或者和前一条消息间隔时间比较长
        var showTimestamp = true
        if (position > 0) {
            showTimestamp = !DateUtils.isCloseEnough(messages[position].msgTime, messages[position - 1].msgTime)
        }
        return showTimestamp
    }

    class SendMessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){}

    class ReceiveMessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){}
}