package com.strugglelin.im.factory

import androidx.fragment.app.Fragment
import com.strugglelin.im.R
import com.strugglelin.im.ui.fragment.ContactFragment
import com.strugglelin.im.ui.fragment.ConversationFragment
import com.strugglelin.im.ui.fragment.MeFragment

/**
 *  @author strugglelin
 *  @date 2020/6/26
 *  description:
 */
class FragmentFactory private constructor() {

    companion object {
        val instance = FragmentFactory()
    }

    val conversationFragment by lazy {
        ConversationFragment()
    }

    val contactFragment by lazy {
        ContactFragment()
    }

    val meFragment by lazy {
        MeFragment()
    }

    fun getFragment(tabId: Int): Fragment? {
        when (tabId) {
            R.id.tab_conversation -> return conversationFragment
            R.id.tab_contacts -> return contactFragment
            R.id.tab_dynamic -> return meFragment
            else -> return null
        }
    }
}