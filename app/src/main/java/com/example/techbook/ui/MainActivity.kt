package com.example.techbook.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.example.techbook.R

class MainActivity : AppCompatActivity(), webViewFragment.Listner {

    override fun move_to_WebView(url: String){
        val bundle = Bundle()
        bundle.putString("URL", url)
        val fragment = webViewFragment()
        fragment.setArguments(bundle)
        val fragmentManager = this.getSupportFragmentManager()
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
            .addToBackStack(null)
            .commit()
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, allCircleFragment())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, searchBottonFragment())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, likeCircleFragment())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        val text = intent.getStringExtra("action")
        if( text == "webview"){
            val url = intent.getStringExtra("URL")
            val bundle = Bundle()
            bundle.putString("URL", url)
            val fragment = webViewFragment()
            fragment.setArguments(bundle)
            navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit()
        } else{
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, allCircleFragment())
                .commit()
        }
    }
}
