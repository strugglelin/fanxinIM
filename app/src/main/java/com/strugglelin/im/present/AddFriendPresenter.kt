package com.strugglelin.im.present

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.hyphenate.chat.EMClient
import com.strugglelin.im.bean.Friend
import com.strugglelin.im.contract.AddFriendContract
import org.jetbrains.anko.doAsync

/**
 *  @author strugglelin
 *  @date 2020/6/27
 *  description:
 */
class AddFriendPresenter(val view :AddFriendContract.View) :AddFriendContract.Presenter {

    val friendList = mutableListOf<Friend>()

    override fun search(key: String) {
        val query = BmobQuery<BmobUser>()
        query.addWhereContains("username", key)
            .addWhereNotEqualTo("username", EMClient.getInstance().currentUser)
        query.findObjects(object : FindListener<BmobUser>() {
            override fun done(p0: MutableList<BmobUser>?, p1: BmobException?) {
                if (p1 == null) {
                    //处理数据
                    //创建AddFriendItem的集合
                    // val allContacts = IMDatabase.instance.getAllContacts()
                    doAsync {
                        friendList.clear()
                        p0?.forEach {
                            //比对是否已经添加过好友
                            var isAdded = false
//                            for (contact in allContacts){
//                                if (contact.name == it.username){
//                                    isAdded = true
//                                    break
//                                }
//                            }
                            val addFriendItem = Friend(it.username, it.createdAt, isAdded)
                            friendList.add(addFriendItem)
                        }
                        uiThread { view.onSearchSuccess() }
                    }
                }
                else view.onSearchFailed()
            }

        })
    }
}