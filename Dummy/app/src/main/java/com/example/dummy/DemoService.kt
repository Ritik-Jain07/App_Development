package com.example.dummy

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class DemoService: FirebaseMessagingService() {

    private val TAG = DemoService::class.java.simpleName

    companion object {
        const val IMAGE_URL_KEY = "image_url"
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        Log.i(TAG,"${p0.messageId}")

        // Check if message contains a notification payload.
        p0.notification?.let {
            //Log.d(TAG, "Message Notification Body: ${it.body}")
            sendNotification(it.title, it.body, p0.data)
        }
    }


    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d(TAG, p0)
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private fun sendNotification(
        messageTitle: String?,
        messageBody: String?,
        data: MutableMap<String, String>
    ) {
        // Setting up tap action
        // Create an explicit intent for an Activity in your app
        val intent = Intent(this, MainActivity::class.java)
        // Attach additional data to Intent
        intent.putExtra(IMAGE_URL_KEY, data[IMAGE_URL_KEY])
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0 /* Request code */, intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val channelId = getString(R.string.default_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_baseline_circle_notifications_24)
            .setContentTitle(messageTitle)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                getString(R.string.channel_name),
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        // Show notification
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }


}
