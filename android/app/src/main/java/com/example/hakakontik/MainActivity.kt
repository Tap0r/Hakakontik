package com.example.hakakontik

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        findViewById<View>(R.id.touchlistener).setOnTouchListener(TouchListener(findViewById(R.id.scrolls)))
    }
}
