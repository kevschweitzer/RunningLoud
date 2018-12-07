package com.dev.kevinschweitzer.musicrunning.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dev.kevinschweitzer.musicrunning.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //Back to original theme after splash screen
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
