package com.example.bullsandcows

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.devInfoButton).setOnClickListener {
            val intent = Intent(this@MainActivity, DevActivity::class.java)
            startActivity(intent)
        }
    }
}