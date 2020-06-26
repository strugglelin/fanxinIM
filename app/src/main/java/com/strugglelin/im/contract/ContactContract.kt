package com.strugglelin.im.contract

/**
 *  @author strugglelin
 *  @date 2020/6/26
 *  description:
 */
interface ContactContract{
    interface Presenter:BasePresenter{
        fun loadContacts()
    }

    interface View{
        fun onLoadContactsSuccess()
        fun onLoadContactFailed()
    }
}