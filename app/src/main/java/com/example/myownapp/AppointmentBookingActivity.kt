package com.example.myownapp

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import java.util.*

class AppointmentBookingActivity : AppCompatActivity() {
    private lateinit var datePicker: DatePicker
    private lateinit var timePicker: TimePicker
    private lateinit var createAppointmentButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_card)
        datePicker = findViewById(R.id.date_picker)
        timePicker = findViewById(R.id.time_picker)
        createAppointmentButton = findViewById(R.id.create_appointment_button)
        createAppointmentButton.setOnClickListener {

            val calendar = Calendar.getInstance().apply {
                set(Calendar.YEAR, datePicker.year)
                set(Calendar.MONTH, datePicker.month)
                set(Calendar.DAY_OF_MONTH, datePicker.dayOfMonth)
                set(Calendar.HOUR_OF_DAY, timePicker.hour)
                set(Calendar.MINUTE, timePicker.minute)
                set(Calendar.SECOND, 0)
            }
            val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            val intent = Intent(this, AppointmentReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val channelId = "AppointmentReminder"
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(channelId, "Appointment Reminder", NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(channel)
            }
            val notification = NotificationCompat.Builder(this, channelId)
                .setContentTitle("Appointment Reminder")
                .setContentText("You have an appointment scheduled at ${calendar.time}")
                .setSmallIcon(R.drawable.notification)
                .build()
            notificationManager.notify(0, notification)
            finish()
        }
    }
}