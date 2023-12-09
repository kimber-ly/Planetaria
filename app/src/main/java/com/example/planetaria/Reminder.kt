package com.example.planetaria

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class Reminder: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("Reminder", "Alarm received!")
        val builder = NotificationCompat.Builder(context, "apod")
            .setSmallIcon(R.drawable.planetaria)
            .setContentTitle("New Apod image is up!")
            .setContentText("Let's see what interesting stuff is going on.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager:
                NotificationManagerCompat = NotificationManagerCompat.from(context)

        if (notificationManager.areNotificationsEnabled()) {
            if (context.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                notificationManager.notify(200, builder.build())
            }
        }
    }
}