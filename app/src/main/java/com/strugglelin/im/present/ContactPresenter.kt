package com.strugglelin.im.present

import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import com.strugglelin.im.contract.ContactContract
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 *  @author strugglelin
 *  @date 2020/6/26
 *  description:
 */
class ContactPresenter(val view:ContactContract.View):ContactContract.Presenter{
    override fun loadContacts() {
        doAsync {
            try {
                val userContacts = EMClient.getInstance().contactManager().allContactsFromServer
                uiThread {
                    view.onLoadContactsSuccess()
                }
            }catch (e:HyphenateException){
                uiThread {
                    view.onLoadContactFailed()
                }
            }
        }
    }

}