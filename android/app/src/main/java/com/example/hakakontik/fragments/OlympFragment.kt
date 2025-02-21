package com.example.hakakontik.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.hakakontik.R


class OlympFragmentNavHost: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.olympiads_navhost, container, false)
    }
}

class OlympFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val inflatedView = inflater.inflate(R.layout.olympiads, container, false)

        inflatedView.findViewById<Button>(R.id.fragmentnaebalo).setOnClickListener{
            val bundle = bundleOf("id" to 0)
            NavHostFragment.findNavController(this).navigate(R.id.publicationFragment, bundle)
        }

        inflatedView.findViewById<Button>(R.id.fragmentnaebalo2).setOnClickListener {
            val bundle = bundleOf("id" to 1)
            NavHostFragment.findNavController(this).navigate(R.id.publicationFragment, bundle)
        }

        inflatedView.findViewById<Button>(R.id.fragmentnaebalo3).setOnClickListener {
            val bundle = bundleOf("id" to 2)
            NavHostFragment.findNavController(this).navigate(R.id.publicationFragment, bundle)
        }

        return inflatedView
    }
}
