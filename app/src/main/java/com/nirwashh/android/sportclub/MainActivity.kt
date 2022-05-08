package com.nirwashh.android.sportclub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nirwashh.android.sportclub.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        val fragment = MainFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment, "MainFragment").commit()
    }
}