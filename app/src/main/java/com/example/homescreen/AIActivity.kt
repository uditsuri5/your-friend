package com.example.homescreen

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.ai.client.generativeai.GenerativeModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.*

class AIActivity : AppCompatActivity() {
    lateinit var editText: EditText
    lateinit var send: FloatingActionButton
    lateinit var moveback: FloatingActionButton
    lateinit var textView: TextView

    // Create a CoroutineScope tied to the lifecycle of the activity
    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai)
        editText = findViewById(R.id.edittext)
        send = findViewById(R.id.send)
        textView = findViewById(R.id.textview_name)

        send.setOnClickListener {
            val inputText = editText.text.toString()

            // Launch a coroutine for network operation
            activityScope.launch {
                // Run the API call and fetching in a background thread
                val response = withContext(Dispatchers.IO) {
                    // Initialize your generative model
                    val generativeModel = GenerativeModel(
                        modelName = "gemini-pro",
                        apiKey = "AIzaSyCeO6OwQ_r5Kvz3DzOBbMIyx8dvOLl138U"
                    )

                    // Perform the API call to generate content
                    generativeModel.generateContent(inputText)
                }

                // After the response is received, update the UI on the main thread
                textView.text = response.text
            }
        }
    }

    // Optional: Make sure to clean up the coroutine scope when the activity is destroyed
    override fun onDestroy() {
        super.onDestroy()
        activityScope.cancel()  // Cancel all running coroutines
    }
}
