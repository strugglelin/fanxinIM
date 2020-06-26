package com.strugglelin.im.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.strugglelin.im.widget.AddFriendListItemView

/**
 *  @author strugglelin
 *  @date 2020/6/27
 *  description:
 */
class AddFriendListAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AddFriendListItemViewHolder(AddFriendListItemView(context))
    }

    override fun getItemCount(): Int = 20

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    class AddFriendListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}