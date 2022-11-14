package com.markvtls.feature_details.presentation

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)

        val fragment = when(position) {
            0 -> DetailsShopFragment()
            1 -> DetailsTextFragment()
            2 -> DetailsFeaturesFragment()
            else -> DetailsShopFragment()
        }
        return fragment
    }
}
