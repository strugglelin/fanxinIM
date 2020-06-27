package com.strugglelin.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.strugglelin.im.R
import com.strugglelin.im.bean.Contact
import kotlinx.android.synthetic.main.view_contact_item.view.*

/**
 *  @author strugglelin
 *  @date 2020/6/26
 *  description:
 */
class ContactListItemView (context: Context,attrs: AttributeSet?=null) : RelativeLayout(context,attrs){

    init {
        View.inflate(context,R.layout.view_contact_item,this)
    }

    fun bindView(contact: Contact){
        if(contact.showFirstLetter){
            firstLetter.visibility = View.VISIBLE
            firstLetter.text = contact.firstLetter.toString()
        }else firstLetter.visibility = View.INVISIBLE
        userName.text = contact.userName
    }

}