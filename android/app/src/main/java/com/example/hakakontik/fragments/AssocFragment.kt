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
import com.example.hakakontik.databinding.AssociationsBinding

class AssocFragmentNavHost: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.associations_navhost, container, false)
    }
}

class AssocFragment: Fragment() {
    private var _binding: AssociationsBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("_binding is null")
    lateinit var listLv: ListView



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = AssociationsBinding.inflate(inflater, container, false)
        val view = binding.root

//

        return view
    }
}