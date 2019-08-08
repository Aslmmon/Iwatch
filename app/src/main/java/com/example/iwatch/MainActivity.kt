package com.example.iwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.example.iwatch.common.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewPager()

    }

    private fun setupViewPager() {
        tabs.addTab(tabs.newTab().setText(getString(R.string.top_rated)))
        tabs.addTab(tabs.newTab().setText(getString(R.string.upcoming)))
        tabs.addTab(tabs.newTab().setText(getString(R.string.now_playing)))
        tabs.addTab(tabs.newTab().setText(getString(R.string.popular_movies)))
        val myAdapter = ViewPagerAdapter(supportFragmentManager, tabs.tabCount)
        viewPager.adapter = myAdapter
        viewPager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(tabs)
        )


    }
}
