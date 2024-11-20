package com.example.homescreen.Authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.homescreen.R

class Forgetpassword : AppCompatActivity() {
    lateinit var forgetemail:EditText
    lateinit var forgetdone:Button
    lateinit var move:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgetpassword)
        forgetemail = findViewById(R.id.forgetemail)
        forgetdone = findViewById(R.id.forgetdone)
        move = findViewById(R.id.move)


        move.setOnClickListener {
            startActivity(Intent(this,Login::class.java))
            finish()
        }
    }
}