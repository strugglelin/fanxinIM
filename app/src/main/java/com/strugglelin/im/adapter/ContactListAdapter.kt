package com.strugglelin.im.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hyphenate.chat.EMClient
import com.strugglelin.fanxinlibrary.adapter.EMCallBackAdapter
import com.strugglelin.im.R
import com.strugglelin.im.bean.Contact
import com.strugglelin.im.ui.activity.ChatActivity
import com.strugglelin.im.widget.ContactListItemView
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

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
        val userName = userContactList[position].userName
        contactListItemView.setOnClickListener{
            context.startActivity<ChatActivity>("userName" to userName)
        }
        contactListItemView.setOnLongClickListener {
            val message = String.format(context.getString(R.string.delete_friend_message), userName)
            AlertDialog.Builder(context)
                .setTitle(R.string.delete_friend_title)
                .setMessage(message)
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.confirm, DialogInterface.OnClickListener { dialogInterface, i ->
                    deleteFriend(userName)
                })
                .show()
            true
        }
    }

    private fun deleteFriend(userName: String) {
        EMClient.getInstance().contactManager().aysncDeleteContact(userName, object : EMCallBackAdapter(){
            override fun onSuccess() {
                context.runOnUiThread { toast(R.string.delete_friend_success) }
            }

            override fun onError(p0: Int, p1: String?) {
                context.runOnUiThread { toast(R.string.delete_friend_failed) }
            }

        })
    }
    class ContractListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}