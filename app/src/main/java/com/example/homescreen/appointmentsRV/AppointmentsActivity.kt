package com.example.homescreen.doctorsRV

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.homescreen.R
import java.text.SimpleDateFormat
import java.util.*

class AppointmentsActivity : ComponentActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var tvAppointmentDate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointments)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("appointments_pref", Context.MODE_PRIVATE)
        tvAppointmentDate = findViewById(R.id.tvAppointmentDate)

        // Retrieve the saved appointment date
        val savedDate = sharedPreferences.getLong("appointment_date", -1)
        if (savedDate != -1L) {
            // Format and display the saved date
            val date = Date(savedDate)
            val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val tvAppointmentDate = findViewById<TextView>(R.id.tvAppointmentDate)

            tvAppointmentDate.text = "Appointment Date: ${formatter.format(date)}"
        } else {
            tvAppointmentDate.text = "No appointment scheduled"
        }
    }
}
