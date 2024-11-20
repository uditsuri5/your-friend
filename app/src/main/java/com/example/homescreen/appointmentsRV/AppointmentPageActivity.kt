package com.example.homescreen.appointmentsRV

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homescreen.R
import com.google.android.material.button.MaterialButton
import java.util.Calendar

class AppointmentPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment_page)

        val etDate = findViewById<EditText>(R.id.et_date)
        val etTime = findViewById<EditText>(R.id.et_time)
        val btnConfirm = findViewById<MaterialButton>(R.id.btn_confirm_appointment)

        val calendar = Calendar.getInstance()

        // DatePicker setup
        etDate.setOnClickListener {
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    // Update the EditText with the selected date
                    etDate.setText("$selectedDay/${selectedMonth + 1}/$selectedYear")
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        // TimePicker setup
        etTime.setOnClickListener {
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(
                this,
                { _, selectedHour, selectedMinute ->
                    // Update the EditText with the selected time
                    val formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                    etTime.setText(formattedTime)
                },
                hour,
                minute,
                true
            )
            timePickerDialog.show()
        }

        // Confirm Appointment
        btnConfirm.setOnClickListener {
            val selectedDate = etDate.text.toString()
            val selectedTime = etTime.text.toString()

            if (selectedDate.isEmpty() || selectedTime.isEmpty()) {
                Toast.makeText(this, "Please select both date and time", Toast.LENGTH_SHORT).show()
            } else {

                Toast.makeText(
                    this,
                    "Appointment confirmed for $selectedDate at $selectedTime",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
