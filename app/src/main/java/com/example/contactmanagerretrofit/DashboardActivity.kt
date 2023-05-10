package com.example.contactmanagerretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.widget.ViewPager2
import com.example.contactmanagerretrofit.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DashboardActivity : AppCompatActivity() {

    private lateinit var displayContactNavHost: NavHostFragment
    private lateinit var addContactNavHost:NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)

        //Create the navhostfragment instance
        displayContactNavHost = NavHostFragment.create(R.navigation.dashboard_display_contact_nav)
        addContactNavHost = NavHostFragment.create(R.navigation.dashboard_add_contact_nav)

        val fragmentHost = listOf(
            addContactNavHost,
            displayContactNavHost
        )
        viewPager.adapter = ViewPagerAdapter(this,fragmentHost)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)

        TabLayoutMediator(tabLayout,viewPager) {
            tab,position ->
            when(position) {
                0 ->tab.text = "Add Contact"
                1 ->tab.text = "Display Contact"
            }
        }.attach()
    }
}