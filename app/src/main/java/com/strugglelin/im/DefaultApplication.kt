package com.strugglelin.im

import android.app.*
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.SoundPool
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.strugglelin.bmoblibrary.BmobCore
import com.strugglelin.fanxinlibrary.IMCore
import com.strugglelin.fanxinlibrary.adapter.EMMessageListenerAdapter
import com.strugglelin.im.factory.NotificationFactory
import com.strugglelin.im.ui.activity.ChatActivity

class DefaultApplication :Application(){

    companion object {
        lateinit var instance :DefaultApplication
    }

    val soundPool = SoundPool(2, AudioManager.STREAM_MUSIC, 0)
    val duan by lazy {
        soundPool.load(instance, R.raw.duan, 0)
    }

    val yulu by lazy {
        soundPool.load(instance, R.raw.yulu, 0)
    }

    val messageListener = object : EMMessageListenerAdapter(){
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            //如果是在前台，则播放短的声音
            if (isForeground()){
                soundPool.play(duan, 1f, 1f, 0, 0, 1f)
            } else {
                //如果在后台，则播放长的声音
                soundPool.play(yulu, 1f, 1f, 0, 0, 1f)
                showNotification(p0)
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        IMCore.init(this)
        BmobCore.init(this)
        EMClient.getInstance().chatManager().addMessageListener(messageListener)
    }

    private fun showNotification(p0: MutableList<EMMessage>?) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        p0?.forEach {
            var contentText = getString(R.string.no_text_message)
            if (it.type == EMMessage.Type.TXT){
                contentText = (it.body as EMTextMessageBody).message
            }
            val intent = Intent(this, ChatActivity::class.java)
            intent.putExtra("userName", it.conversationId())
//            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            val taskStackBuilder = TaskStackBuilder.create(this).addParentStack(ChatActivity::class.java).addNextIntent(intent)
            val pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)

            NotificationFactory.instance
                .contentTitle(getString(R.string.receive_new_message))
                .contentText(contentText)
                .largeIcon(R.mipmap.avatar1)
                .smallIcon(R.mipmap.ic_contact)
                .pendingIntent(pendingIntent)
                .showNotification(this)
//            val notification = Notification.Builder(this)
//                .setContentTitle(getString(R.string.receive_new_message))
//                .setContentText(contentText)
//                .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.avatar1))
//                .setSmallIcon(R.mipmap.ic_contact)
//                .setContentIntent(pendingIntent)
//                .setAutoCancel(true)
//                .notification
//            notificationManager.notify(1, notification)
        }
    }


    private fun isForeground(): Boolean{
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (runningAppProcess in activityManager.runningAppProcesses) {
            if (runningAppProcess.processName == packageName){
                //找到了app的进程
                return runningAppProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
            }
        }
        return false
    }
}