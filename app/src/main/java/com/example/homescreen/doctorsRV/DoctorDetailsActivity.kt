package com.example.homescreen.doctorsRV

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.homescreen.R

class DoctorDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_details)

        val doctorName = intent.getStringExtra("doctorName")
        val doctorSpeciality = intent.getStringExtra("doctorSpeciality")

        val tvDoctorName = findViewById<TextView>(R.id.tvDoctorName)
        val tvDoctorDetails = findViewById<TextView>(R.id.tvDoctorDetails)

        tvDoctorName.text = doctorName
        tvDoctorDetails.text = """
            Speciality: $doctorSpeciality
            Achievements:
            - Recognized as one of the top professionals in $doctorSpeciality.
            - Published multiple papers in international journals.
            - Member of global organizations in the $doctorSpeciality field.
        """.trimIndent()
    }
}
