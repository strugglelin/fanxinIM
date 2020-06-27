package com.strugglelin.im.ui.fragment

import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.hyphenate.chat.EMClient
import com.strugglelin.fanxinlibrary.adapter.EMContactListenerAdapter
import com.strugglelin.im.R
import com.strugglelin.im.adapter.ContactListAdapter
import com.strugglelin.im.contract.ContactContract
import com.strugglelin.im.present.ContactPresenter
import com.strugglelin.im.ui.activity.AddFriendActivity
import com.strugglelin.im.widget.SlideBar
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 *  @author strugglelin
 *  @date 2020/6/26
 *  description:
 */

class ContactFragment : BaseFragment(), ContactContract.View {

    val presenter = ContactPresenter(this);
    val contactListener = object : EMContactListenerAdapter() {

        override fun onContactDeleted(p0: String?) {
            //重新获取联系人数据
            presenter.loadContacts()
        }

        override fun onContactAdded(p0: String?) {
            //重新获取联系人数据
            presenter.loadContacts()
        }
    }

    override fun init() {
        headerTitle.text = getString(R.string.contact)
        add.visibility = View.VISIBLE

        add.setOnClickListener {
            context?.startActivity<AddFriendActivity>()
        }

        swipeRefreshLayout.apply {
            setColorSchemeColors(ContextCompat.getColor(context, R.color.qq_blue))
            isRefreshing = true
            setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
                override fun onRefresh() {
                    presenter.loadContacts()
                }
            })
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ContactListAdapter(context, presenter.userContactList)
        }

        EMClient.getInstance().contactManager().setContactListener(contactListener)

        presenter.loadContacts()

        slideBar.onSectionChangeListener = object : SlideBar.OnSectionChangeListener {
            override fun onSectionChange(firstLetter: String) {
                section.visibility = View.VISIBLE
                section.text = firstLetter
                Log.e("tag", "Position=" + getPosition(firstLetter))
                val position = getPosition(firstLetter)
                if (position != -1) recyclerView.smoothScrollToPosition(position)
            }

            override fun onSlideFinish() {
                section.visibility = View.GONE
            }
        }
    }

    private fun getPosition(firstLetter: String): Int {
        var position = -1
        presenter.userContactList.forEachIndexed() { index, contact ->
            if (contact.firstLetter.equals(firstLetter[0], true)) {
                position = index
                return position
            }
        }
        return position
    }
//    private fun getPosition(firstLetter: String): Int = presenter.userContactList.binarySearch {
//                contactListItem ->  contactListItem.firstLetter.minus(firstLetter[0])
//        }

    override fun onLoadContactsSuccess() {
        swipeRefreshLayout?.isRefreshing = false
        recyclerView?.adapter?.notifyDataSetChanged()
    }

    override fun onLoadContactFailed() {
        swipeRefreshLayout.isRefreshing = false
        context?.toast(getString(R.string.load_contacts_failed))
    }

    override fun getLayoutResId(): Int = R.layout.fragment_contacts


}