package com.example.planetaria.mars

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.planetaria.mars.photo.MarsPhotoFragment
import com.example.planetaria.mars.realEstate.RealEstateFragment

class MarsViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MarsPhotoFragment()
            1 -> RealEstateFragment()
            else -> MarsPhotoFragment()
        }
    }
}
