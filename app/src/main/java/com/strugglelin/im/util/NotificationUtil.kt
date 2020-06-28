package com.strugglelin.im.util

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.strugglelin.im.R
import com.strugglelin.im.ui.activity.ChatActivity
import com.strugglelin.im.ui.activity.MainActivity


/**
 *  @author strugglelin
 *  @date 2020/6/28
 *  description:
 */
object NotificationUtil {

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

    fun sendSimpleNotification(context: Context) {

        val id = 1000
        val groupId = "groupId_1"
        val groupName = "渠道组名1"
        val channelId = "channelId_1"
        val channelName = "渠道名1"

        val builder = NotificationCompat.Builder(context, channelId)

        builder.setContentTitle("简单的通知的标题")
            .setContentText("这是通知内容")
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    context.getResources(),
                    R.mipmap.ic_launcher
                )
            )
            .setAutoCancel(true)// 自己维护通知的消失
            .setSmallIcon(R.mipmap.ic_launcher)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        //构建一个Intent
        val resultIntent = Intent(context, MainActivity::class.java)
        //        resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //        resultIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        // 封装一个Intent
        val resultPendingIntent = PendingIntent.getActivity(
            context, 0, resultIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        builder.setContentIntent(resultPendingIntent)

        // 这里必须设置chanenelId,要不然该通知在8.0手机上，不能正常显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannelId(context, groupId, groupName, channelId, channelName)
            builder.setChannelId(channelId)
        }

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(id, builder.build())
    }

    fun sendSimpleNotificationSecond(context: Context) {
        val id = 1002
        val groupId = "groupId_2"
        val groupName = "渠道组名2"
        val channelId = "channelId_2"
        val channelName = "渠道名2"
        val builder = Notification.Builder(context)
        builder.setContentTitle("简单的通知的标题2")
            .setContentText("这是通知内容2")
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    context.getResources(), R.mipmap.ic_launcher
                )
            )
            .setAutoCancel(true)
            .setSmallIcon(R.mipmap.ic_launcher)

        //构建一个Intent
        val resultIntent = Intent(context, ChatActivity::class.java)
        //        resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //        resultIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        // 封装一个Intent
        val resultPendingIntent = PendingIntent.getActivity(
            context, 0, resultIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        builder.setContentIntent(resultPendingIntent)

        // 这里必须设置chanenelId,要不然该通知在8.0手机上，不能正常显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannelId(context, groupId, groupName, channelId, channelName)
            builder.setChannelId(channelId)
        }

        //发送通知
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(id, builder.build())
    }

    //    当Channel已经存在时，后面的createNotificationChannel方法仅能更新其name/description，
    //    以及对importance进行降级，其余配置均无法更新。所以如果有必要的修改只能创建新的渠道，删除旧渠道
    fun deleteNotificationChannel(context: Context, channelId: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            mNotificationManager.deleteNotificationChannel(channelId)
        }
    }

}

