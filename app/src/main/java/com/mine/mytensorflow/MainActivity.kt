package com.mine.mytensorflow

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.support.v4.app.Fragment


class MainActivity : AppCompatActivity() {

    private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_home -> {
                loadFragment(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                loadFragment(ClassificationFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                loadFragment(AboutFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(HomeFragment())
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_container, fragment)
                .commit()
            return true
        }
        return false
    }
}
