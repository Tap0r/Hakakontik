package com.example.hakakontik

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.hakakontik.fragments.ViewPagerAdapter


class MainActivity : FragmentActivity() {
    // viewPager для смены фрагментов на экране
    private val viewPager: ViewPager2 by lazy { findViewById(R.id.viewpager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        viewPager.adapter = ViewPagerAdapter(this)
        // обработка смены фрагмента (красит кнопки)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            private var oldPos = 0
            private val buttons: Array<ImageButton> by lazy {
                arrayOf(findViewById(R.id.olympiads), findViewById(R.id.events), findViewById(R.id.associations))
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Anim.unActive(buttons[oldPos])
                Anim.active(buttons[position])
                oldPos = position
            }
        })


    }

    // жмём кнопку и viewPager меняет фрагмент; кнопка реагирует на нажатие увеличением размера
    fun onOlympClick(view: View) { viewPager.currentItem = 0; Anim.scale(view) }
    fun onEventClick(view: View) { viewPager.currentItem = 1; Anim.scale(view) }
    fun onAssocClick(view: View) { viewPager.currentItem = 2; Anim.scale(view) }
}
