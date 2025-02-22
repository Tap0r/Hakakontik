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
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.widget.SearchView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavDirections
import androidx.recyclerview.widget.RecyclerView
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager

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
    lateinit var searchView: SearchView
    lateinit var listLv: RecyclerView
    private val channelId = "i.apps.notifications" // Unique channel ID for notifications
    private val description = "Test notification"  // Description for the notification channel
    private val notificationId = 1234


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        _binding = OlympiadsBinding.inflate(layoutInflater)
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = OlympiadsBinding.inflate(inflater, container, false)
        val view = binding.root
        var cnt = 0

        // access the listView from xml file
        listLv = binding.OlymList


        val database = Firebase.database
        val ref = database.getReference("olimp")

        val action: (Int, String) -> NavDirections = { id, ref ->
            OlympFragmentDirections.actionOlympFragmentToPublicationFragment(id, ref)


        }

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                        if(cnt>1)
                        {
                            scheduleNotification(requireContext())
                        }
                cnt++



                val value = dataSnapshot.value as ArrayList<Map<String, String>>
                val adapter = FirebaseListAdapter(value, "olimp", action)
                listLv.adapter = adapter

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("test", "Failed to read value.", error.toException())
            }
        })








        return view
    }

    fun scheduleNotification(context: Context) {
        // Создаем OneTimeWorkRequest для NotificationWorker
        val notificationWorkRequest = OneTimeWorkRequest.Builder(MyWorker::class.java).build()


        // Запускаем WorkManager
        WorkManager.getInstance(context).enqueue(notificationWorkRequest)
    }
}