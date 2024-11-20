package com.example.homescreen.Test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.homescreen.R
import com.example.homescreen.DatabaseHelper

class TestInstructions : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_instructions)  // Ensure this is the correct layout

        val userEmail = intent.getStringExtra("USER_EMAIL") ?: ""

        // Find the TextView for displaying the test score
        val scoreTextView = findViewById<TextView>(R.id.scoreTextView)

        // Retrieve the latest test score for the logged-in user from the database
        val dbHelper = DatabaseHelper(this)
        val latestScore = dbHelper.getLatestTestScore(userEmail)

        // Display the test score for the user
        scoreTextView.text = "Your Score: $latestScore"

        // Set up the button to start the test
        val btnTest = findViewById<Button>(R.id.btnTest)
        btnTest.setOnClickListener {
            val intent = Intent(this, TestMainActivity::class.java)
            intent.putExtra("USER_EMAIL", userEmail)
            startActivity(intent)
        }
    }
}
