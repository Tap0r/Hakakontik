package com.example.hakakontik

import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener

class TouchListener(private val scrolls: View): OnTouchListener {
    private var x: Float = 0F
    private var dx: Float = 0F
    private var id: Int = 0
    private var scrollsX: Float = 0F

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                x = event.x
                dx = 0F
                scrollsX = scrolls.x
            }
            MotionEvent.ACTION_MOVE -> {
                dx = x - event.getX(0)
            }
            MotionEvent.ACTION_UP -> {
                
            }
        }
        scrolls.x = scrollsX - dx

        v.performClick()
        return true
    }
}
