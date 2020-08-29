package com.ljt.katp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ljt.katp.server.MainService


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = Intent(this, MainService::class.java)
        startService(service)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}