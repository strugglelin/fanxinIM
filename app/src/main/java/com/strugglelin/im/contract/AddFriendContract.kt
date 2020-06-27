package com.strugglelin.im.contract

/**
 *  @author strugglelin
 *  @date 2020/6/27
 *  description:
 */
interface AddFriendContract{

    interface Presenter:BasePresenter{
        fun search(key:String)
    }

    interface View{
        fun onSearchSuccess()
        fun onSearchFailed()
    }
}