package com.example.homescreen.Test

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homescreen.DatabaseHelper
import com.example.homescreen.databinding.ActivityMainQuizBinding

class TestMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainQuizBinding
    private lateinit var dbHelper: DatabaseHelper

    private val dass21Questions = arrayOf(
        // Stress questions
        "I found it hard to wind down.",
        "I tended to over-react to situations.",
        "I felt that I was using a lot of nervous energy.",
        "I found it difficult to relax.",
        "I was intolerant of anything that kept me from getting on with what I was doing.",
        "I felt that I was rather touchy.",
        "I felt that I was unable to become enthusiastic about anything.",

        // Anxiety questions
        "I was aware of dryness of my mouth.",
        "I experienced breathing difficulty (e.g., excessively rapid breathing, breathlessness in the absence of physical exertion).",
        "I experienced trembling (e.g., in the hands).",
        "I was worried about situations in which I might panic and make a fool of myself.",
        "I felt I was close to panic.",
        "I was aware of the action of my heart in the absence of physical exertion (e.g., sense of heart rate increase, heart missing a beat).",
        "I felt scared without any good reason.",

        // Depression questions
        "I couldn't seem to experience any positive feeling at all.",
        "I found it difficult to work up the initiative to do things.",
        "I felt that I had nothing to look forward to.",
        "I felt down-hearted and blue.",
        "I was unable to become enthusiastic about anything.",
        "I felt I wasn't worth much as a person.",
        "I felt that life was meaningless."
    )

    private val options = arrayOf(
        "Did not apply to me at all",
        "Applied to me to some degree, or some of the time",
        "Applied to me to a considerable degree, or a good part of the time",
        "Applied to me very much, or most of the time"
    )

    private var currentQuestionIndex = 0
    private var questionNum = 1
    private var selectedResponse: String? = null
    private var totalScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)
        val userEmail = intent.getStringExtra("USER_EMAIL") ?: ""

        displayQuestions()

        // Setting click listeners for options
        binding.btnOption1.setOnClickListener { selectedOption(0) }
        binding.btnOption2.setOnClickListener { selectedOption(1) }
        binding.btnOption3.setOnClickListener { selectedOption(2) }
        binding.btnOption4.setOnClickListener { selectedOption(3) }

        // Next question or completion
        binding.buttonSubmit.setOnClickListener { nextQuestion(userEmail) }
    }

    private fun selectedOption(index: Int) {
        selectedResponse = options[index]
        totalScore += index // Directly add the score (0-3) corresponding to the option
        resetButtonColors()
        when (index) {
            0 -> binding.btnOption1.setBackgroundColor(Color.parseColor("#219EBC"))
            1 -> binding.btnOption2.setBackgroundColor(Color.parseColor("#219EBC"))
            2 -> binding.btnOption3.setBackgroundColor(Color.parseColor("#219EBC"))
            3 -> binding.btnOption4.setBackgroundColor(Color.parseColor("#219EBC"))
        }
    }

    private fun resetButtonColors() {
        binding.btnOption1.setBackgroundColor(Color.TRANSPARENT)
        binding.btnOption2.setBackgroundColor(Color.TRANSPARENT)
        binding.btnOption3.setBackgroundColor(Color.TRANSPARENT)
        binding.btnOption4.setBackgroundColor(Color.TRANSPARENT)
    }

    private fun displayQuestions() {
        binding.tvQuestion.text = dass21Questions[currentQuestionIndex]
        binding.btnOption1.text = options[0]
        binding.btnOption2.text = options[1]
        binding.btnOption3.text = options[2]
        binding.btnOption4.text = options[3]
        binding.tvQuestionNum.text = "Question $questionNum"
        binding.progressBar.max = dass21Questions.size
        binding.progressBar.progress = questionNum
        resetButtonColors()
    }

    private fun nextQuestion(userEmail: String) {
        if (selectedResponse == null) {
            Toast.makeText(this, "Please select an option!", Toast.LENGTH_SHORT).show()
            return
        }

        // Save the user's response temporarily
        selectedResponse = null

        if (currentQuestionIndex < dass21Questions.size - 1) {
            currentQuestionIndex++
            questionNum++
            displayQuestions()
        } else {
            showResult(userEmail)
        }
    }

    private fun showResult(userEmail: String) {
        // Save the latest test score in the database
        dbHelper.updateLatestTestScore(userEmail, totalScore, questionNum)

        // Show a message to the user
        Toast.makeText(this, "Test completed! Your score: $totalScore", Toast.LENGTH_LONG).show()

        // Navigate to result review activity
        val intent = Intent(this, TestInstructions::class.java)
        intent.putExtra("USER_EMAIL", userEmail)
        intent.putExtra("TOTAL_SCORE", totalScore)
        startActivity(intent)

        // Finish the test activity
        finish()
    }
}
