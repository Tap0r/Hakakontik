package com.example.hakakontik.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int { return 3 }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OlympFragment()
            1 -> EventFragment()
            2 -> AssocFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}
