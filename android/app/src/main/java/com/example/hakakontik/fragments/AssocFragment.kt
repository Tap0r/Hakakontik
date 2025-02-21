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

class AssocFragmentNavHost: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.associations_navhost, container, false)
    }
}

class AssocFragment: Fragment() {
//    private var _binding: AssociationsBinding? = null
//    private val binding
//        get() = _binding ?: throw IllegalStateException("_binding is null")
    lateinit var listLv: ListView

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        _binding = AssociationsBinding.inflate(layoutInflater)
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.associations, container, false)
        val arrayAdapter: ArrayAdapter<*>
        val users = arrayOf(
            "as1", "as2", "as3", "as4", "as5", "as6", "as7", "as8",
            "as9", "as10", "as11", "as12", "as13", "as14", "as15", "as16")

        // access the listView from xml file
        listLv = view.findViewById(R.id.AssList)
        arrayAdapter = ArrayAdapter(requireContext(), R.layout.list_item_style, users)
        listLv.adapter = arrayAdapter
        listLv.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(requireContext(), parent.getPositionForView(view).toString(), Toast.LENGTH_SHORT).show()
        }

        return view
    }
}