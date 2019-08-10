package com.example.iwatch


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.iwatch.common.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
    }

    private fun setupViewPager() {
        tabs.addTab(tabs.newTab().setText(getString(R.string.top_rated)))
        tabs.addTab(tabs.newTab().setText(getString(R.string.upcoming)))
        tabs.addTab(tabs.newTab().setText(getString(R.string.now_playing)))
        tabs.addTab(tabs.newTab().setText(getString(R.string.popular_movies)))
        val myAdapter = ViewPagerAdapter(activity!!.supportFragmentManager, tabs.tabCount)
        viewPager.adapter = myAdapter
        viewPager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(tabs)
        )
        tabs.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {
            }
            override fun onTabReselected(tab: TabLayout.Tab) {
            }

        })
    }
}
