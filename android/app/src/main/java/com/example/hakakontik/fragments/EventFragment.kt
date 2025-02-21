package com.example.hakakontik.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.recyclerview.widget.RecyclerView
import com.example.hakakontik.R
import com.example.hakakontik.databinding.EventsBinding
import com.example.hakakontik.utils.FirebaseListAdapter
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database


class EventFragmentNavHost: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.events_navhost, container, false)
    }
}


class EventFragment: Fragment() {
    private var _binding: EventsBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("_binding is null")

    lateinit var listLv: RecyclerView

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        _binding = EventsBinding.inflate(layoutInflater)
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = EventsBinding.inflate(inflater, container, false)
        val view = binding.root

//        val arrayAdapter: ArrayAdapter<*>
//        val users = arrayOf(
//            "ev1", "ev2", "ev3", "ev4", "ev5", "ev6", "ev7", "ev8",
//            "ev9", "ev10", "ev11", "ev12", "ev13", "ev14", "ev15", "ev16"
//        )

        // access the listView from xml file
        listLv = binding.NewsList
//        arrayAdapter = ArrayAdapter(requireContext(), R.layout.list_item_style, users)
//        listLv.adapter = arrayAdapter

        val database = Firebase.database
        val ref = database.getReference("news")

        val action: (Int, String) -> NavDirections = { id, ref ->
            EventFragmentDirections.actionEventFragmentToPublicationFragment(id, ref)
        }

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.value as ArrayList<Map<String, String>>
                val adapter = FirebaseListAdapter(value, "news", action)
                listLv.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("test", "Failed to read value.", error.toException())
            }
        })

//        listLv.setOnItemClickListener { parent, view, position, id ->
//
//            Toast.makeText(requireContext(), parent.getPositionForView(view).toString(), Toast.LENGTH_SHORT).show()
//        }

        return view
    }
}
