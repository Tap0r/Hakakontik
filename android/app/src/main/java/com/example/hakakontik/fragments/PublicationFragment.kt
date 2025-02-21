package com.example.hakakontik.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.hakakontik.databinding.PublicationBinding
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database


class PublicationFragment: Fragment() {
    private var _binding: PublicationBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("_binding is null")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = PublicationBinding.inflate(inflater, container, false)
        val inflatedView = binding.root

        val id = PublicationFragmentArgs.fromBundle(requireArguments()).id
        val ref = PublicationFragmentArgs.fromBundle(requireArguments()).ref
        val title = binding.title
        val full_description = binding.publicationtext
        val pub_date = binding.pubDate

        val database = Firebase.database
        val databaseReference = database.getReference("$ref/$id")

        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.value as Map<String, String>
                title.text = value["title"]
                full_description.text = value["full_description"]
                pub_date.text = value["date"]
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("test", "Failed to read value.", error.toException())
            }
        })

        binding.publicationbutton.setOnClickListener {
            NavHostFragment.findNavController(this).popBackStack()
        }

        return inflatedView
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val id = arguments?.getInt("id")
//        textV.text = when(id){
//            0 -> "матье балл"
//            1 -> "какашки это вкусно"
//            2 -> "я семь"
//            else -> throw IllegalStateException()
//        }
//    }
}
