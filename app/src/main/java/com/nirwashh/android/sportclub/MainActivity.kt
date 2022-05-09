package com.nirwashh.android.sportclub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.nirwashh.android.sportclub.data.DatabaseManager
import com.nirwashh.android.sportclub.data.TAG
import com.nirwashh.android.sportclub.databinding.ActivityMainBinding
import com.nirwashh.android.sportclub.model.Member

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