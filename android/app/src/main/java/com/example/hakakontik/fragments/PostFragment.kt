package com.example.hakakontik.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hakakontik.databinding.FragmentPostBinding
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PostFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, sI: Bundle?): View {
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        val view = binding.root
        val database = Firebase.database
        val myRef = database.getReference("olimp")
        var dict = mutableMapOf("date" to "xui","full_description" to "xui", "link" to "xui","short_description" to "xui","title" to "xui")
        binding.create.setOnClickListener{
            myRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = dataSnapshot.childrenCount
                    val date = binding.dateDay.text.toString() + "." + binding.dateMonth.text.toString() + ".20" + binding.dateYear.text.toString()
                    dict["date"] = date
                    val full_desc = binding.fullDesc.text.toString()
                    dict["full_description"] = full_desc
                    val link = binding.link.text.toString()
                    dict["link"] = link
                    val short_desc = binding.shortDesc.text.toString()
                    dict["short_description"] = short_desc
                    val title = binding.title.text.toString()
                    dict["title"] = title
                    myRef.child(dataSnapshot.childrenCount.toString()).setValue(dict)

//                    Log.d(TAG, "Value is: $value")
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
//                    Log.w(TAG, "Failed to read value.", error.toException())
                }
            })

        }

        // Inflate the layout for this fragment
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PostFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PostFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}