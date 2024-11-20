package com.example.homescreen.doctorsRV

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homescreen.R
import com.example.homescreen.databinding.ActivityMainBinding

class MainActivityDoctors : ComponentActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.doctors_activity_main)

        var rvDoctors = findViewById<RecyclerView>(R.id.rvDoctors)

        var doctorsList = mutableListOf(
            Doctors("Dr. Shekhar Pal", "Psychiatrist", "22 years", "MBBS", "Rs. 8000",
                R.drawable.doc_icon
            ),
            Doctors("Dr. Piyush Kumar", "Psychologist", "15 years", "MBBS, MD", "Rs. 10000",
                R.drawable.doc_icon
            ),
            Doctors("Dr. Mahindra", "Psychiatrist", "15 years", "MBBS, DNB", "Rs. 12000",
                R.drawable.doc_icon
            ),
            Doctors("Dr. Rohit Sharma", "Psychiatrist", "27 years", "MBBS, MD, MCCS", "Rs. 10000",
                R.drawable.doc_icon
            ),
            Doctors("Dr. Sujal Patidar", "Psychiatrist", "29 years", "MBBS", "Rs. 8000", R.drawable.doc_icon),
            Doctors("Dr. Mayank Gupta", "Psychiatrist", "12 years", "MBBS, DNB", "Rs. 10000",
                R.drawable.doc_icon
            ),
            Doctors("Dr. Vikash Pandey", "Psychiatrist", "29 years", "MBBS, DNB", "Rs. 12000",
                R.drawable.doc_icon
            ),
            Doctors("Dr. Ashok Mittal", "Psychiatrist", "42 years", "MBBS, MD", "Rs. 10000",
                R.drawable.doc_icon
            )
        )

        val adapter = DoctorsAdapter(doctorsList)
        rvDoctors.adapter = adapter
        rvDoctors.layoutManager = LinearLayoutManager(this)
    }


}