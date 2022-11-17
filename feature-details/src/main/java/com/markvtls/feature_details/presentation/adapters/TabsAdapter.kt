package com.markvtls.feature_details.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.markvtls.feature_details.presentation.fragments.DetailsFeaturesFragment
import com.markvtls.feature_details.presentation.fragments.DetailsShopFragment
import com.markvtls.feature_details.presentation.fragments.DetailsTextFragment

/**Adapter for Details TabLayout.*/
internal class TabsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    //fragments quantity
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        // Returns a NEW fragment instance in createFragment(int)

        val fragment = when (position) {
            0 -> DetailsShopFragment()
            1 -> DetailsTextFragment()
            2 -> DetailsFeaturesFragment()
            else -> DetailsShopFragment()
        }
        return fragment
    }
}
