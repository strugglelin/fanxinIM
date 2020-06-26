package com.strugglelin.im.ui.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.strugglelin.im.R
import com.strugglelin.im.adapter.AddFriendListAdapter
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*

/**
 *  @author strugglelin
 *  @date 2020/6/27
 *  description:
 */
class AddFriendActivity : BaseActivity() {
    override fun getLayoutResId(): Int = R.layout.activity_add_friend

    override fun init() {
        headerTitle.text = getString(R.string.add_friend)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = AddFriendListAdapter(context)
        }
    }
}