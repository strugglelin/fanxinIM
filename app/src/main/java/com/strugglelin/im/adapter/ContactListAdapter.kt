package com.strugglelin.im.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.strugglelin.im.widget.ContactListItemView

/**
 *  @author strugglelin
 *  @date 2020/6/26
 *  description:
 */
class ContactListAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ContractListItemViewHolder(ContactListItemView(context));
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    class ContractListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}