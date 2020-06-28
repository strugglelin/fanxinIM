package com.strugglelin.im.factory

import android.app.*
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.strugglelin.im.R

/**
 *  @author strugglelin
 *  @date 2020/6/28
 *  description:
 */

class NotificationFactory {

    private var groupId: String = "渠道组id"
    private var groupName = "渠道组名"
    private var channelId = "渠道id"
    private var channelName = "渠道名"

    private var contentTitle = "通知的标题"
    private var contentText = "通知的内容"
    private var largeIcon = R.mipmap.avatar1
    private var smallIcon = R.mipmap.ic_contact

    private var pendingIntent: PendingIntent? = null

    companion object {
        val instance = NotificationFactory()
    }

    fun groupId(_groupId: String): NotificationFactory {
        groupId = _groupId
        return this
    }

    fun groupName(_groupName: String): NotificationFactory {
        groupName = _groupName
        return this
    }

    fun channelId(_channelId: String): NotificationFactory {
        channelId = _channelId
        return this
    }

    fun channelName(_channelName: String): NotificationFactory {
        channelName = _channelName
        return this
    }

    fun contentTitle(_contentTitle: String): NotificationFactory {
        contentTitle = _contentTitle
        return this
    }

    fun contentText(_contentText: String): NotificationFactory {
        contentText = _contentText
        return this
    }

    fun largeIcon(_largeIcon: Int): NotificationFactory {
        largeIcon = _largeIcon
        return this
    }

    fun smallIcon(_smallIcon: Int): NotificationFactory {
        smallIcon = _smallIcon
        return this
    }

    fun pendingIntent(_pendingIntent: PendingIntent): NotificationFactory {
        pendingIntent = _pendingIntent
        return this
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createChannelId(context: Context, groupId: String, groupName: String, channelId: String, channelName: String) {

        // 分组（可选）groupId 要唯一
        val ncp1 = NotificationChannelGroup(groupId, groupName)

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannelGroup(ncp1)

        // channelId 要唯一
        val chan = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        chan.lightColor = Color.GREEN
        chan.group = groupId
        // VISIBILITY_PRIVATE：表面只有当没有锁屏的时候才能够显示,VISIBILITY_PUBLIC：表明任何情况下都会显示,VISIBILITY_SECRET：表明在pin，password等安全锁和没有锁屏的情况下才能够显示
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        notificationManager.createNotificationChannel(chan)
    }

    fun showNotification(context: Context) {

        val id = 1000

        val builder = NotificationCompat.Builder(context, channelId)

        builder.setContentTitle(contentTitle)
            .setContentText(contentText)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    context.getResources(),
                    largeIcon
                )
            )
            .setSmallIcon(smallIcon)
            .setAutoCancel(true)// 自己维护通知的消失
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        builder.setContentIntent(pendingIntent)

        // 这里必须设置chanenelId,要不然该通知在8.0手机上，不能正常显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannelId(context, groupId, groupName, channelId, channelName)
            builder.setChannelId(channelId)
        }

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(id, builder.build())
    }

}
