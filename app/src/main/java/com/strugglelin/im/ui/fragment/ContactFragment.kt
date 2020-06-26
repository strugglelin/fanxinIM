package com.strugglelin.im.ui.fragment

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.strugglelin.im.R
import com.strugglelin.im.adapter.ContactListAdapter
import com.strugglelin.im.contract.ContactContract
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

/**
 *  @author strugglelin
 *  @date 2020/6/26
 *  description:
 */

class ContactFragment : BaseFragment(), ContactContract.View {

    override fun init() {
        headerTitle.text = getString(R.string.contact)
        add.visibility = View.VISIBLE

        swipeRefreshLayout.apply {
            setColorSchemeColors(ContextCompat.getColor(context, R.color.qq_blue))
            isRefreshing = true
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ContactListAdapter(context)
        }
    }

    override fun onLoadContactsSuccess() {
        swipeRefreshLayout.isRefreshing = false
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onLoadContactFailed() {
        swipeRefreshLayout.isRefreshing = false
        context?.toast(getString(R.string.load_contacts_failed))
    }

    override fun getLayoutResId(): Int = R.layout.fragment_contacts


}