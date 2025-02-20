package com.example.hakakontik

import android.animation.ObjectAnimator
import android.graphics.drawable.TransitionDrawable
import android.view.View
import android.widget.ImageButton

class Anim {
    companion object{
        fun scale(view: View){
            ObjectAnimator.ofFloat(view, "ScaleX", 1.1F, 1F).apply { duration = 200; start() }
            ObjectAnimator.ofFloat(view, "ScaleY", 1.1F, 1F).apply { duration = 200; start() }
        }
        fun active(imBtn: ImageButton){
            val d = imBtn.drawable
            if(d is TransitionDrawable)
                d.startTransition(200)
        }
        fun unActive(imBtn: ImageButton){
            val d = imBtn.drawable
            if(d is TransitionDrawable)
                d.resetTransition()
        }
    }
}