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


class EventFragmentNavHost: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.events_navhost, container, false)
    }
}


class EventFragment: Fragment() {
//    private var _binding: EventsBinding? = null
//    private val binding
//        get() = _binding ?: throw IllegalStateException("_binding is null")
    lateinit var listLv: ListView

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        _binding = EventsBinding.inflate(layoutInflater)
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.events, container, false)
        val arrayAdapter: ArrayAdapter<*>
        val users = arrayOf(
            "ev1", "ev2", "ev3", "ev4", "ev5", "ev6", "ev7", "ev8",
            "ev9", "ev10", "ev11", "ev12", "ev13", "ev14", "ev15", "ev16"
        )

        // access the listView from xml file
        listLv = view.findViewById(R.id.NewsList)
        arrayAdapter = ArrayAdapter(requireContext(), R.layout.list_item_style, users)
        listLv.adapter = arrayAdapter
        listLv.setOnItemClickListener { parent, view, position, id ->

            Toast.makeText(requireContext(), parent.getPositionForView(view).toString(), Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
