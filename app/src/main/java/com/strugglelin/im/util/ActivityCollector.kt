package com.strugglelin.im.util

import android.app.Activity

/**
 *  @author strugglelin
 *  @date 2020/6/28
 *  description:
 */
object ActivityCollector {

    var activities: MutableList<Activity> = ArrayList()

    fun addActivity(activity: Activity) {
        activities.add(activity)
    }

    fun removeActivity(activity: Activity) {
        activities.remove(activity)
    }

    fun finishAll() {
        for (activity in activities) {
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
        activities.clear()
    }

    fun exitApp() {
        finishAll()
        android.os.Process.killProcess(android.os.Process.myPid())
    }

}
