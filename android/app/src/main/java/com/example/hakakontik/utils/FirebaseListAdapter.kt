package com.example.hakakontik.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.hakakontik.databinding.ListItemStyleBinding

class FirebaseListAdapter(private val items: ArrayList<Map<String, String>>,
                          private val ref: String,
                          private val action: (Int, String) -> NavDirections) :
    RecyclerView.Adapter<FirebaseListAdapter.ViewHolder>() {

    class ViewHolder(binding: ListItemStyleBinding) : RecyclerView.ViewHolder(binding.root) {
        var title = binding.title
        var shortDescription = binding.shortDescrip
        var date = binding.date
        var root = binding.root
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
        holder.title.text = item["title"]
        holder.shortDescription.text = item["short_description"]
        holder.date.text = item["date"]
        holder.root.setOnClickListener {
//            val action = OlympFragmentDirections.actionOlympFragmentToPublicationFragment(position)
            holder.root.findNavController().navigate(action(position, ref))

        }

    }


}