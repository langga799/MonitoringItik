package com.example.monitoringitik

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, IpAddressActivity::class.java))
            finishAffinity()
        }, 2000L)

    }
}