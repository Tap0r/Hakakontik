package com.example.hakakontik.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.hakakontik.R


class PublicationFragment(): Fragment() {
    lateinit var textV: TextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val inflatedView = inflater.inflate(R.layout.publication, container, false)
        textV = inflatedView.findViewById(R.id.publicationtext)

        inflatedView.findViewById<Button>(R.id.publicationbutton).setOnClickListener {
            NavHostFragment.findNavController(this).popBackStack()
        }

        return inflatedView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getInt("id")
        textV.text = when(id){
            0 -> "матье балл"
            1 -> "какашки это вкусно"
            2 -> "я семь"
            else -> throw IllegalStateException()
        }
    }
}
