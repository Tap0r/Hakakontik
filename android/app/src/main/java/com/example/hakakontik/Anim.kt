package com.example.hakakontik

import android.animation.ObjectAnimator
import android.view.View
import android.widget.ImageButton

class Anim {
    companion object{
        fun scale(view: View){
            ObjectAnimator.ofFloat(view, "ScaleX", 1.1F, 1F).apply { duration = 100; start() }
            ObjectAnimator.ofFloat(view, "ScaleY", 1.1F, 1F).apply { duration = 100; start() }
        }
        fun active(view: ImageButton){
            view.setImageResource(R.drawable.olymp_blue)
        }
        fun unActive(view: ImageButton){

        }
    }
}