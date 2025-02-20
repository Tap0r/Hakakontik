package com.example.hakakontik.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.hakakontik.R
import com.example.hakakontik.databinding.OlympiadsBinding

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build

import android.widget.Button
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService

import com.example.hakakontik.NotificationActivity


class OlympFragment: Fragment() {
    private var _binding: OlympiadsBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("_binding is null")
    lateinit var listLv: ListView
    private val channelId = "i.apps.notifications" // Unique channel ID for notifications
    private val description = "Test notification"  // Description for the notification channel
    private val notificationId = 1234

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = OlympiadsBinding.inflate(layoutInflater)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.olympiads, container, false)
        val arrayAdapter: ArrayAdapter<*>
        val users = arrayOf(
            "ol1", "ol2", "ol3", "ol4", "ol5", "ol6", "ol7", "ol8",
            "ol9", "ol10", "ol11", "ol12", "ol13", "ol14", "ol15", "ol16"
        )

        // access the listView from xml file
        listLv = view.findViewById(R.id.OlymList)
        arrayAdapter = ArrayAdapter(requireContext(), R.layout.list_item_style, users)
        listLv.adapter = arrayAdapter
        createNotificationChannel()
        listLv.setOnItemClickListener {parent, view, position, id ->
            // Request runtime permission for notifications on Android 13 and higher
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        requireActivity(),
                        arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                        101
                    )
                    return@setOnItemClickListener
                }
            }
            sendNotification() // Trigger the notification
        }






        return view
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelId,
                description,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                enableLights(true) // Turn on notification light
                lightColor = Color.GREEN
                enableVibration(true) // Allow vibration for notifications
            }

            val notificationManager = requireActivity().getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
    @SuppressLint("MissingPermission", "NotificationPermission")
    private fun sendNotification() {
        // Intent that triggers when the notification is tapped
        val intent = Intent(requireContext(),NotificationActivity::class.java ).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            requireContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Custom layout for the notification content


        // Build the notification
        val builder = NotificationCompat.Builder(requireContext(), channelId)
            .setSmallIcon(R.drawable.olympiads) // Notification icon

            .setContentTitle("Пригуби") // Title displayed in the notification
            .setContentText("Вялого") // Text displayed in the notification
            .setContentIntent(pendingIntent) // Pending intent triggered when tapped
            .setAutoCancel(true) // Dismiss notification when tapped
            .setPriority(NotificationCompat.PRIORITY_HIGH) // Notification priority for better visibility

        // Display the notification
        with(NotificationManagerCompat.from(requireContext())) {
            notify(notificationId, builder.build())
        }
    }
}