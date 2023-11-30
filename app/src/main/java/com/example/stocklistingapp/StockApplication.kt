package com.example.stocklistingapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import com.example.stocklistingapp.common.constants.Notification
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class StockApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()
        subscribeTopic()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                Notification.NOTIFICATION_CHANNEL_ID,
                Notification.NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Just to send notification, you know!"
                enableLights(true)
                lightColor = Color.RED
            }
            getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        }
    }
    private fun subscribeTopic() {
        FirebaseMessaging.getInstance().subscribeToTopic(Notification.TOPIC)
    }
}