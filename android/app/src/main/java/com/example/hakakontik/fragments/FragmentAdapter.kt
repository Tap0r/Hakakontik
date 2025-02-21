package com.example.hakakontik.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment = when(position) {
        0 -> OlympFragmentNavHost()
        1 -> EventFragmentNavHost()
        2 -> AssocFragmentNavHost()
        3 -> ProfileFragmentNavHost()
        else -> throw IllegalStateException()
    }
}
