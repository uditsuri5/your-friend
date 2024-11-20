package com.example.homescreen.appointmentsRV

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.homescreen.R

class AppointmentsItem : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.items_appointments)
        val tvName = findViewById<TextView>(R.id.tvName)
        val tvSpeciality = findViewById<TextView>(R.id.tvSpeciality)
        val tvDate = findViewById<TextView>(R.id.tvDate)
        val tvTime = findViewById<TextView>(R.id.tvTime)
        val ivImage = findViewById<ImageView>(R.id.ivImage)
    }
}
