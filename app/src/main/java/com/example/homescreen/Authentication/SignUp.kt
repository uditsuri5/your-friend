package com.example.homescreen.Authentication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homescreen.DatabaseHelper
import com.example.homescreen.MainActivity
import com.example.homescreen.R
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signUpButton: Button
    private lateinit var moveToLoginButton: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Initialize UI components
        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
        signUpButton = findViewById(R.id.button)
        moveToLoginButton = findViewById(R.id.movetologin)

        // Initialize Firebase Auth and DatabaseHelper
        auth = FirebaseAuth.getInstance()
        dbHelper = DatabaseHelper(this)

        moveToLoginButton.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            finish()
        }

        signUpButton.setOnClickListener {
            performSignup()
        }
    }

    private fun performSignup() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Fields cannot be empty!", Toast.LENGTH_LONG).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Store email in the local database
                    dbHelper.insertResponse(email, "Welcome!", "Account created successfully")

                    Toast.makeText(this, "Signup successful!", Toast.LENGTH_LONG).show()

                    // Redirect to MainActivity
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("USER_EMAIL", email)
                    startActivity(intent)
                    finish()
                } else {
                    Log.w(TAG, "Signup failure", task.exception)
                    Toast.makeText(this, "Signup failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }

    companion object {
        private const val TAG = "SignUpActivity"
    }
}
