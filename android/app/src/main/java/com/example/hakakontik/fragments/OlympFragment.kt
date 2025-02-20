package com.example.hakakontik.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.hakakontik.R


class OlympFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val inflatedView: View = inflater.inflate(R.layout.olympiads, container, false)
        val btn: Button = inflatedView.findViewById(R.id.fragmentnaebalo)
        btn.setOnClickListener {
            btn.text = "хуище"
        }
        return inflatedView
    }
}
