package com.strugglelin.im.bean.db

/**
 *  @author strugglelin
 *  @date 2020/6/27
 *  description:
 */
data class Contact(val map:MutableMap<String,Any?>){

    val _id by map
    val name by map
}