package com.example.hakakontik

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.hakakontik.fragments.ViewPagerAdapter


class MainActivity : FragmentActivity() {
    private val viewPager: ViewPager2 by lazy { findViewById(R.id.viewpager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        viewPager.adapter = ViewPagerAdapter(this)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when(position){
                    0 -> findViewById<ImageButton>(R.id.olympiads)
                    1 -> findViewById<ImageButton>(R.id.olympiads)
                    2 -> findViewById<ImageButton>(R.id.olympiads)
                }
            }
        })
    }

    fun onOlympClick(view: View?) { viewPager.currentItem = 0 }
    fun onEventClick(view: View?) { viewPager.currentItem = 1 }
    fun onAssocClick(view: View?) { viewPager.currentItem = 2 }
}
