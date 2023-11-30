package com.example.stocklistingapp.common.firebase_cloud_messaging

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.compose.ui.platform.AndroidUiDispatcher.Companion.Main
import androidx.core.app.NotificationCompat
import com.example.stocklistingapp.R
import com.example.stocklistingapp.common.constants.Notification
import com.example.stocklistingapp.common.extensions.Preferences
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.random.Random

@AndroidEntryPoint
class FirebaseCloudMessagingService : FirebaseMessagingService() {

    @Inject
    lateinit var shp: SharedPreferences

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        shp.edit().putString(Preferences.TOKEN, token).apply()
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        showNotification(message)
    }

    private fun showNotification(message: RemoteMessage) {
        val intent = Intent(this, Main::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(this, Notification.NOTIFICATION_CHANNEL_ID)
            .setContentTitle(message.notification?.title)
            .setContentText(message.notification?.body)
            .setSmallIcon(R.drawable.app_logo)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(Random.nextInt(), notification)
    }
}