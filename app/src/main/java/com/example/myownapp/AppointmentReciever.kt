package com.example.myownapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.myownapp.R

class AppointmentReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "AppointmentReminder"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Appointment Reminder", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }
        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle("Appointment Reminder")
            .setContentText("You have an appointment scheduled now")
            .setSmallIcon(R.drawable.notification)
            .build()
        notificationManager.notify(0, notification)
    }
}