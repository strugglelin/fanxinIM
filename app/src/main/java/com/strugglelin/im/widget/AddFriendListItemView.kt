package com.strugglelin.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.strugglelin.im.R

/**
 *  @author strugglelin
 *  @date 2020/6/27
 *  description:
 */
class AddFriendListItemView(context: Context, attrs:AttributeSet?=null ) : RelativeLayout(context,attrs){

    init {
        View.inflate(context, R.layout.view_add_friend_item,this)
    }
}
