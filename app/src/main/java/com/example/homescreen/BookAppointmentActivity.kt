package com.example.homescreen.doctorsRV

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homescreen.R
import com.google.android.material.datepicker.MaterialDatePicker

class BookAppointmentActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var btnSaveAppointment: Button
    private lateinit var btnOpenDatePicker: Button
    private lateinit var btnPrescription: Button
    private var selectedDate: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_appointment)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("appointments_pref", Context.MODE_PRIVATE)

        // Find views
        btnSaveAppointment = findViewById(R.id.btnSaveAppointment)
        btnOpenDatePicker = findViewById(R.id.btnOpenDatePicker)
        btnPrescription = findViewById(R.id.btnPrescription)

        // Set up the DatePicker
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Appointment Date")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

        // Open DatePicker when the button is clicked
        btnOpenDatePicker.setOnClickListener {
            datePicker.show(supportFragmentManager, "DATE_PICKER")
        }

        // Handle DatePicker selection
        datePicker.addOnPositiveButtonClickListener { selection ->
            selectedDate = selection
            Toast.makeText(this, "Selected Date: ${datePicker.headerText}", Toast.LENGTH_SHORT).show()
        }

        // Save the selected date to SharedPreferences
        btnSaveAppointment.setOnClickListener {
            if (selectedDate != null) {
                saveAppointmentDate(selectedDate!!)
                Toast.makeText(this, "Appointment saved successfully!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please select a date first!", Toast.LENGTH_SHORT).show()
            }
        }

        // Set listener for btnPrescription
        btnPrescription.setOnClickListener {
            bookAppointment(it)
        }
    }

    // Save the date to SharedPreferences
    private fun saveAppointmentDate(date: Long) {
        val editor = sharedPreferences.edit()
        editor.putLong("appointment_date", date)
        editor.apply()
    }

    // Method referenced in android:onClick attribute
    fun bookAppointment(view: View) {
        Toast.makeText(this, "Prescription clicked!", Toast.LENGTH_SHORT).show()
    }
}
