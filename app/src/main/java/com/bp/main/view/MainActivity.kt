package com.bp.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bp.hilt.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(
            R.id.frame_layout,
            MainFragment()
        ).commit()
    }
}