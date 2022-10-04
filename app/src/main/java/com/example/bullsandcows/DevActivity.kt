package com.example.bullsandcows

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DevActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dev)
        findViewById<Button>(R.id.home_button).setOnClickListener {
            val intent = Intent(this@DevActivity, MainActivity::class.java)
            startActivity(intent)
    }
    }
}