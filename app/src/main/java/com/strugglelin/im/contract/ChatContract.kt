package com.strugglelin.im.contract

import com.hyphenate.chat.EMMessage

/**
 *  @author strugglelin
 *  @date 2020/6/28
 *  description:
 */
interface ChatContract{

    interface Presenter:BasePresenter{
        fun sendMessage(contact:String,message:String)
        fun addMessage(username: String, p0: MutableList<EMMessage>?)
        fun loadMessages(username: String)
        fun loadMoreMessages(username: String)
    }

    interface View{
        fun onStartSendMessage()
        fun onSendMessageSuccess()
        fun onSendMessageFailed()
        fun onMessageLoaded()
        fun onMoreMessageLoaded(size: Int)
    }
}