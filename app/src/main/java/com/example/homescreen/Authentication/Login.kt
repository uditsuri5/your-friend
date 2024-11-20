package com.example.homescreen.Authentication

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homescreen.DatabaseHelper
import com.example.homescreen.MainActivity
import com.example.homescreen.R
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var signUpButton: Button
    private lateinit var forgetPasswordButton: TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize UI elements
        emailEditText = findViewById(R.id.emaillogin)
        passwordEditText = findViewById(R.id.firstpassword)
        loginButton = findViewById(R.id.loginbutton)
        signUpButton = findViewById(R.id.loginsingup)
        forgetPasswordButton = findViewById(R.id.forgetpassword)

        // Initialize Firebase Auth and DatabaseHelper
        auth = FirebaseAuth.getInstance()
        dbHelper = DatabaseHelper(this)

        // Navigate to SignUp Activity
        signUpButton.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
        }

        // Navigate to ForgetPassword Activity
        forgetPasswordButton.setOnClickListener {
            startActivity(Intent(this, Forgetpassword::class.java))
        }

        // Check if a user is already logged in
        auth.currentUser?.let { currentUser ->
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("USER_EMAIL", currentUser.email)
            startActivity(intent)
            finish()
        }

        // Login button listener
        loginButton.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email!", Toast.LENGTH_SHORT).show()
            return
        }

        if (password.isEmpty()) {
            Toast.makeText(this, "Password cannot be empty!", Toast.LENGTH_SHORT).show()
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Redirect to MainActivity
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("USER_EMAIL", email) // Pass email to MainActivity
                    startActivity(intent)
                    finish()
                    Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Login failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }
}
