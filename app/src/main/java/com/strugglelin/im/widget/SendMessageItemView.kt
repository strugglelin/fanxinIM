package com.strugglelin.im.widget

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import com.strugglelin.im.R
import kotlinx.android.synthetic.main.view_send_message_item.view.*
import java.util.*

/**
 *  @author strugglelin
 *  @date 2020/6/28
 *  description:
 */
class SendMessageItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    init {
        View.inflate(context, R.layout.view_send_message_item, this)
    }

    fun bindView(emMessage: EMMessage, showTimestamp: Boolean) {
        updateTimestamp(emMessage,showTimestamp)
        updateMessage(emMessage)
        updateProgress(emMessage)
    }

    private fun updateTimestamp(emMessage: EMMessage,showTimestamp: Boolean) {
        if(showTimestamp){
            timestamp.visibility = View.VISIBLE
            timestamp.text = DateUtils.getTimestampString(Date(emMessage.msgTime))
        }else{
            timestamp.visibility = View.GONE
        }
    }

    private fun updateMessage(emMessage: EMMessage) {
        if (emMessage.type == EMMessage.Type.TXT) {
            sendMessage.text = (emMessage.body as EMTextMessageBody).message
        } else {
            sendMessage.text = context.getString(R.string.no_text_message)
        }
    }

    private fun updateProgress(emMessage: EMMessage) {
        emMessage.status().let {
            when (it) {
                EMMessage.Status.INPROGRESS -> {
                    sendMessageProgress.visibility = View.VISIBLE
                    sendMessageProgress.setImageResource(R.drawable.send_message_progress)
                    val animationDrawable = sendMessageProgress.drawable as AnimationDrawable
                    animationDrawable.start()
                }
                EMMessage.Status.SUCCESS -> {
                    sendMessageProgress.visibility = View.GONE
                }
                EMMessage.Status.FAIL->{
                    sendMessageProgress.visibility = View.VISIBLE
                    sendMessageProgress.setImageResource(R.mipmap.msg_error)
                }
            }
        }
    }

}

