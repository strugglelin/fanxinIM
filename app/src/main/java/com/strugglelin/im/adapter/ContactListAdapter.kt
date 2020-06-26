package com.strugglelin.im.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.strugglelin.im.bean.Contact
import com.strugglelin.im.widget.ContactListItemView

/**
 *  @author strugglelin
 *  @date 2020/6/26
 *  description:
 */
class ContactListAdapter(
    val context: Context,
    val userContactList: MutableList<Contact>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ContractListItemViewHolder(ContactListItemView(context));
    }

    override fun getItemCount(): Int {
        return userContactList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val contactListItemView = holder.itemView as ContactListItemView
        contactListItemView.bindView(userContactList[position])
    }

    class ContractListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}