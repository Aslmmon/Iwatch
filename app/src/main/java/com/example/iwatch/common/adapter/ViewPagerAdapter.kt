package com.example.iwatch.common.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.iwatch.features.now_playing_fragment.NowPlayingMoviesFragment
import com.example.iwatch.features.popular_movies_fragment.PopularMoviesFragment
import com.example.iwatch.features.top_rated_fragment.TopRatedMoviesFragment
import com.example.iwatch.features.upcoming_fragment.UpcomingMoviesFragment

class ViewPagerAdapter(fm: FragmentManager,private var tabCount: Int) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return TopRatedMoviesFragment()
            1 -> return UpcomingMoviesFragment()
            2 -> return NowPlayingMoviesFragment()
            3 -> return PopularMoviesFragment()
            else -> return TopRatedMoviesFragment()
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}