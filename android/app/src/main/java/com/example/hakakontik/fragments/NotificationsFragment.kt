package com.example.hakakontik.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.example.hakakontik.databinding.FragmentNotificationsBinding
import com.example.hakakontik.utils.FirebaseListAdapter
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NotificationsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotificationsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
//    var list:MutableList<Map<String,String>> = mutableListOf()
    val favs = arrayListOf<String>()
    var list = arrayListOf<Map<String,String>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val action: (Int, String) -> NavDirections = { id, ref ->
            NotificationsFragmentDirections.actionNotificationsFragmentToPublicationFragment2(id, ref)


        }
        binding.btnBack.setOnClickListener {
            NavHostFragment.findNavController(this).popBackStack()
        }
        val listLv = binding.OlymList
        val database = Firebase.database
        val myRef = database.getReference("/")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if (dataSnapshot.value != null){
                    if (dataSnapshot.value!!::class == HashMap::class) {
                        val value = dataSnapshot.child("favorite/map").value as ArrayList<String>
                        favs.addAll(value)
                    }
                    else {
                        val value = dataSnapshot.child("favorite").value as ArrayList<String>
                        favs.addAll(value)
                    }

                }
                if ( !(dataSnapshot.child("favorite/map").getValue() == null)){
                    val value:ArrayList<String> = dataSnapshot.child("favorite/map").getValue() as ArrayList<String>


                    for (item in value){
                        val path:String = "olimp/" + item[0]
                        Log.d("xui", "Value is: $path")
                        list.add(dataSnapshot.child(path).getValue<Map<String,String>>()!!)
                        Log.d("xui", "Value is: ${list}")
                    }
                    val adapter = FirebaseListAdapter(list, favs, "olimp", action)
                    listLv.adapter = adapter
                    list = arrayListOf()
                }



//                Log.d(TAG, "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })

        return binding.root
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NotificationsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NotificationsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}