package com.mine.mytensorflow

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.support.v4.app.Fragment
import android.content.Intent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val gotoClassification = 1200

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_home -> {
                loadFragment(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_classification -> {
                startActivityForResult(Intent(this, ClassificationActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION), gotoClassification)
                return@OnNavigationItemSelectedListener false
            }
            R.id.navigation_about -> {
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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == gotoClassification) {
            if (resultCode == Activity.RESULT_OK) {
                data?.let {
                    val pos = it.getIntExtra("pos", 0)
                    when(pos){
                        0 -> nav_view.selectedItemId = R.id.navigation_home
                        2 -> nav_view.selectedItemId = R.id.navigation_about
                    }
                }

            }
        }
    }
}
