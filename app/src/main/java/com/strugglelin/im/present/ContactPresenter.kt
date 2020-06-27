package com.strugglelin.im.present

import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import com.strugglelin.im.bean.Contact
import com.strugglelin.im.bean.db.IMDatabase
import com.strugglelin.im.contract.ContactContract
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 *  @author strugglelin
 *  @date 2020/6/26
 *  description:
 */
class ContactPresenter(val view:ContactContract.View):ContactContract.Presenter{

    val userContactList = mutableListOf<Contact>()
    override fun loadContacts() {
        doAsync {
            try {
                val userContacts = EMClient.getInstance().contactManager().allContactsFromServer
                // 根据首字母排序
                userContacts.sortBy { it[0] }
                // 联系人列表
                userContactList.clear()
                IMDatabase.instance.deleteAllContacts()
                userContacts.forEachIndexed() { index,s->
                    // 判断是否显示首字符
                    val showFirstLetter = index==0 || s[0]!= userContacts[index-1][0]
                    val contact = Contact(s,s[0].toUpperCase(),showFirstLetter)
                    userContactList.add(contact)

                    val _contact = com.strugglelin.im.bean.db.Contact(mutableMapOf("name" to s))
                    IMDatabase.instance.saveContact(_contact)
                }

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