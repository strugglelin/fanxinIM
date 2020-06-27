package com.strugglelin.im.extentions

fun String.isValidUserName():Boolean = this.matches(Regex("^[a-zA-Z]\\w{2,19}$"))
fun String.isVaildPassWord():Boolean = this.matches(Regex("^[0-9]{3,20}$"))

fun<K,V> MutableMap<K,V>.toVarargArray():Array<Pair<K,V>> =
        // 将MutableMap转化为Pair类型数组
        map{
            Pair(it.key,it.value)
        }.toTypedArray()