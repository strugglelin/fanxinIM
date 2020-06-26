package com.strugglelin.im.ui.fragment

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.strugglelin.im.R
import com.strugglelin.im.adapter.ContactListAdapter
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*

/**
 *  @author strugglelin
 *  @date 2020/6/26
 *  description:
 */

class ContactFragment : BaseFragment(){

    override fun init() {
        headerTitle.text = getString(R.string.contact)
        add.visibility = View.VISIBLE

        swipeRefreshLayout.apply {
            setColorSchemeColors(ContextCompat.getColor(context,R.color.qq_blue))
            isRefreshing = true
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ContactListAdapter(context)
        }
    }

    override fun getLayoutResId(): Int = R.layout.fragment_contacts

}