package com.example.homescreen

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        // Set action bar title and background color
        supportActionBar?.title = "Profile"
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#219EBC")))

        // Retrieve user email from intent
        val userEmail = intent.getStringExtra("USER_EMAIL") ?: ""

        // Add functionality for results button
        val resultsButton = findViewById<Button>(R.id.resultsButton) // Ensure this is defined in profile.xml
        resultsButton.setOnClickListener {
            // Intent to navigate to PastResultsActivity
            val intent = Intent(this, PastResultsActivity::class.java)
            intent.putExtra("USER_EMAIL", userEmail)  // Pass the email or any other data
            startActivity(intent)
        }
    }
}
