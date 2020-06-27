package com.strugglelin.im.ui.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.strugglelin.im.R
import com.strugglelin.im.adapter.AddFriendListAdapter
import com.strugglelin.im.contract.AddFriendContract
import com.strugglelin.im.present.AddFriendPresenter
import kotlinx.android.synthetic.main.activity_add_friend.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

/**
 *  @author strugglelin
 *  @date 2020/6/27
 *  description:
 */
class AddFriendActivity : BaseActivity(),AddFriendContract.View {

    val presenter = AddFriendPresenter(this)

    override fun getLayoutResId(): Int = R.layout.activity_add_friend

    override fun init() {
        headerTitle.text = getString(R.string.add_friend)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = AddFriendListAdapter(context,presenter.friendList)
        }

        search.setOnClickListener{
            search()
        }

        userName.setOnEditorActionListener{view, id, event ->
            search()
            true
        }
    }

    fun search(){
        hideSoftKeyBoard()
        showProgress(getString(R.string.searching))
        val key = userName.text.trim().toString()
        presenter.search(key)
    }

    override fun onSearchSuccess() {
        dismissProgress()
        toast(getString(R.string.search_success))
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onSearchFailed() {
        dismissProgress()
        toast(getString(R.string.search_failed))
    }
}