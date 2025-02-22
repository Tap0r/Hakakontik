package com.example.hakakontik.utils

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.hakakontik.databinding.ListItemStyleBinding
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import java.util.Dictionary

class FirebaseListAdapter(private val items: ArrayList<Map<String, String>>,
                          private val favs: ArrayList<String>?,
                          private val ref: String,
                          private val action: (Int, String) -> NavDirections) :
    RecyclerView.Adapter<FirebaseListAdapter.ViewHolder>() {

    class ViewHolder(binding: ListItemStyleBinding) : RecyclerView.ViewHolder(binding.root) {
        var title = binding.title
        var shortDescription = binding.shortDescrip
        var date = binding.date
        var root = binding.root
        var isFav = binding.isFav
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemStyleBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        try {
            if (favs != null) {
                if (favs.isNotEmpty()) {
                    val target = favs.filter { it.startsWith(position.toString())  }
                    val check = target[0][2]
                    holder.isFav.isChecked = (check != '0')
                }

            }
        } catch (ex: IndexOutOfBoundsException) {
            holder.isFav.isChecked = false
        }



        holder.title.text = item["title"]
        holder.shortDescription.text = item["short_description"]
        holder.date.text = item["date"]
        holder.root.setOnClickListener {
            holder.root.findNavController().navigate(action(position, ref))
        }
        val database = Firebase.database
        val favoriteReference = database.getReference("favorite")
        val rootRef = database.getReference("/")
        var res: ArrayList<String>
        holder.isFav.setOnClickListener {

            favoriteReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.value == null) {
                        val initVal = arrayListOf("${holder.bindingAdapterPosition} ${if (holder.isFav.isChecked) "1" else "0"}")
                        favoriteReference.child("map").setValue(initVal)
                    }
                    else {
                        val value = dataSnapshot.child("map").value as ArrayList<String>
//                        Log.d("test", "1${temp}")
//                        Log.d("test", "2${temp::class.simpleName}")
//                        Log.d("test", "3${temp[1]}")
//                        Log.d("test", "4${temp[0]::class.simpleName}")
//                        val temp0 = temp[0]
//                        var value = temp.toMutableMap()

                        if (holder.isFav.isChecked) {

                            value.add("${holder.bindingAdapterPosition} 1")
                            favoriteReference.child("map").setValue(value)
                        }
                        else {
//                            val regex = Regex("^1")
                            value.removeAll{ it[0].toString() == holder.bindingAdapterPosition.toString() }
                            favoriteReference.child("map").setValue(value)
                        }
                    }


                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("test", "Failed to read value.", error.toException())
                }
            })

        }
    }


}