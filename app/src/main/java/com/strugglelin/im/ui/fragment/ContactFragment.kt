package com.strugglelin.im.ui.fragment

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.strugglelin.im.R
import com.strugglelin.im.adapter.ContactListAdapter
import com.strugglelin.im.contract.ContactContract
import com.strugglelin.im.present.ContactPresenter
import com.strugglelin.im.ui.activity.AddFriendActivity
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

    override fun init() {
        headerTitle.text = getString(R.string.contact)
        add.visibility = View.VISIBLE

        add.setOnClickListener{
            context?.startActivity<AddFriendActivity>()
        }

        swipeRefreshLayout.apply {
            setColorSchemeColors(ContextCompat.getColor(context, R.color.qq_blue))
            isRefreshing = true
            setOnRefreshListener(object :SwipeRefreshLayout.OnRefreshListener{
                override fun onRefresh() {
                    presenter.loadContacts()
                }
            })
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ContactListAdapter(context,presenter.userContactList)
        }

        presenter.loadContacts()
    }

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