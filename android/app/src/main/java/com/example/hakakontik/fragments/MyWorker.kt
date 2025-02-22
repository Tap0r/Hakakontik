package com.example.hakakontik.fragments

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {




    override fun doWork(): Result {

        // Создаем уведомление
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Создаем канал для уведомлений (требуется для Android 8.0 и выше)
        val channelId = "my_channel_id"
        val channelName = "My Channel"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(channelId, channelName, importance)
        notificationManager.createNotificationChannel(channel)


        val database = Firebase.database
        val ref = database.getReference("olimp")

        ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value:Map<String,String> = dataSnapshot.child((dataSnapshot.childrenCount-1).toString()).getValue()!! as Map<String,String>
                val title = value["title"]
                val short_description = value["short_description"]

                val notification = NotificationCompat.Builder(applicationContext, channelId)

                    .setContentTitle(title)
                    .setContentText(short_description)
                    .setSmallIcon(android.R.drawable.ic_notification_overlay) // Иконка уведомления



                    .build()
                notificationManager.notify(1, notification)


            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("test", "Failed to read value.", error.toException())
            }
    })
        return Result.success()
}
}