package com.example.homescreen

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PastResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_past_results)

        val userEmail = intent.getStringExtra("USER_EMAIL") ?: ""
        val dbHelper = DatabaseHelper(this)

        // Retrieve all test scores for the user
        val testScores = dbHelper.getTestScoresForUser(userEmail)

        // Populate the TextView with the test scores, displaying "Test 1: Score", "Test 2: Score", etc.
        val resultsTextView = findViewById<TextView>(R.id.resultsTextView)
        val resultsText = if (testScores.isNotEmpty()) {
            testScores.joinToString("\n") { "Test ${it.first}: ${it.second}" }
        } else {
            "No test scores available"
        }

        resultsTextView.text = resultsText
    }
}
