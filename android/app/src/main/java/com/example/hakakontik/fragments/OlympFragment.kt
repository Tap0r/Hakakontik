package com.example.hakakontik.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hakakontik.R
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavDirections
import androidx.recyclerview.widget.RecyclerView
import com.example.hakakontik.MainActivity
import com.example.hakakontik.databinding.OlympiadsBinding
import com.example.hakakontik.utils.FirebaseListAdapter
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database


class OlympFragmentNavHost: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.olympiads_navhost, container, false)
    }
}

class OlympFragment: Fragment() {
    private var _binding: OlympiadsBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("_binding is null")

    lateinit var listLv: RecyclerView
    private val channelId = "i.apps.notifications" // Unique channel ID for notifications
    private val description = "Test notification"  // Description for the notification channel
    private val notificationId = 1234

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        _binding = OlympiadsBinding.inflate(layoutInflater)
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = OlympiadsBinding.inflate(inflater, container, false)
        val view = binding.root

        // access the listView from xml file
        listLv = binding.OlymList


        val database = Firebase.database
        val olimpRef = database.getReference("olimp")
        val favRef = database.getReference("favorite/")
        val favMapRef = database.getReference("favorite/map")
        val favs = arrayListOf<String>()

        favRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.value != null){
                    if (dataSnapshot.value!!::class == HashMap::class) {
                        val value = dataSnapshot.child("map").value as ArrayList<String>
                        favs.addAll(value)
                    }
                    else {
                        val value = dataSnapshot.value as ArrayList<String>
                        favs.addAll(value)
                    }

                }

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("test", "Failed to read value.", error.toException())
            }
        })

        val action: (Int, String) -> NavDirections = { id, ref ->
            OlympFragmentDirections.actionOlympFragmentToPublicationFragment(id, ref)
        }

        olimpRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.value as ArrayList<Map<String, String>>
                val adapter = FirebaseListAdapter(value, favs, "olimp", action)
                listLv.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("test", "Failed to read value.", error.toException())
            }
        })


//        val users = arrayOf(
//            "ol1", "ol2", "ol3", "ol4", "ol5", "ol6", "ol7", "ol8",
//            "ol9", "ol10", "ol11", "ol12", "ol13", "ol14", "ol15", "ol16")
//        val arrayAdapter: ArrayAdapter<*>
//        arrayAdapter = ArrayAdapter(requireContext(), R.layout.list_item_style, users)
//        listLv.adapter = arrayAdapter


        createNotificationChannel()
//        listLv.setOnItemClickListener {parent, view, position, id ->
//            // Request runtime permission for notifications on Android 13 and higher
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                if (ActivityCompat.checkSelfPermission(
//                        requireContext(),
//                        Manifest.permission.POST_NOTIFICATIONS
//                    ) != PackageManager.PERMISSION_GRANTED
//                ) {
//                    ActivityCompat.requestPermissions(
//                        requireActivity(),
//                        arrayOf(Manifest.permission.POST_NOTIFICATIONS),
//                        101
//                    )
//                    return@setOnItemClickListener
//                }
//            }
//            sendNotification() // Trigger the notification
//        }

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
        val intent = Intent(requireContext(),MainActivity::class.java ).apply {
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