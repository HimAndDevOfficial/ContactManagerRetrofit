package com.example.contactmanagerretrofit.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.contactmanagerretrofit.AddContactFragment
import com.example.contactmanagerretrofit.DisplayContactFragment

class ViewPagerAdapter(activity: FragmentActivity, private val fragmentList:List<Fragment>) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int) = fragmentList[position]

}
