package com.mine.mytensorflow

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.telecom.Call
import android.app.Activity



class ClassificationActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_home -> {
                val intent = Intent()
                intent.putExtra("pos", 0)
                setResult(Activity.RESULT_OK, intent)
                finish()
                overridePendingTransition(0, 0)
            }
            R.id.navigation_classification -> {
                return@OnNavigationItemSelectedListener false
            }
            R.id.navigation_about -> {
                val intent = Intent()
                intent.putExtra("pos", 2)
                setResult(Activity.RESULT_OK, intent)
                finish()
                overridePendingTransition(0, 0)
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classification)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.selectedItemId = R.id.navigation_classification
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(0, 0)
    }
}
