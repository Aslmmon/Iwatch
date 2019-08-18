package com.example.iwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.viewpager.widget.ViewPager
import com.example.iwatch.common.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import io.reactivex.plugins.RxJavaPlugins



class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RxJavaPlugins.setErrorHandler { throwable ->
            Log.i("Main","error is ${throwable.message}")
        } // nothing or some logging

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        setUpBottomNavMenu(navController)


    }

    private fun setUpBottomNavMenu(navController: NavController) {
        bottom_nav?.let {
            NavigationUI.setupWithNavController(it, navController)
        }
    }

}
