package com.example.presentsir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val loginButton: Button = findViewById(R.id.registerbutton)

        loginButton.setOnClickListener {
            val intent = Intent(this, Finger::class.java)
            startActivity(intent)
        }


        }
}
