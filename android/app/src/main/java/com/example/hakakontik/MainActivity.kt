package com.example.hakakontik

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.widget.ViewPager2
import com.example.hakakontik.fragments.ViewPagerAdapter


class MainActivity : FragmentActivity() {
    private val viewPager: ViewPager2 by lazy { findViewById(R.id.viewpager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        viewPager.adapter = ViewPagerAdapter(this)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            private var oldPos = 0
            private val buttons: Array<ImageButton> by lazy {
                arrayOf(findViewById(R.id.olympiads), findViewById(R.id.events), findViewById(R.id.associations), findViewById(R.id.profile))
            }
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Anim.unActive(buttons[oldPos])
                Anim.active(buttons[position])
                oldPos = position
            }
        })
    }

    fun onOlympClick(view: View) { viewPager.currentItem = 0; Anim.scale(view) }
    fun onEventClick(view: View) { viewPager.currentItem = 1; Anim.scale(view) }
    fun onAssocClick(view: View) { viewPager.currentItem = 2; Anim.scale(view) }
    fun onProfileClick(view: View) { viewPager.currentItem = 3; Anim.scale(view) }
}
