package com.strugglelin.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.cardview.widget.CardView
import com.strugglelin.im.R

/**
 *  @author strugglelin
 *  @date 2020/6/26
 *  description:
 */
class ContactListItemView (context: Context,attrs: AttributeSet?=null) : CardView(context,attrs){

    init {
        View.inflate(context,R.layout.view_contact_item,this)
    }
}