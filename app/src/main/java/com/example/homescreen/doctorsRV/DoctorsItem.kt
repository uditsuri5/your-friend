package com.example.homescreen.doctorsRV

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.homescreen.R


class DoctorsItem : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.items_doctors)

        val tvName = findViewById<TextView>(R.id.tvName)
        var tvSpeciality = findViewById<TextView>(R.id.tvSpeciality)
        var tvExperience = findViewById<TextView>(R.id.tvExperience)
        var tvDegree = findViewById<TextView>(R.id.tvDegree)
        var tvPrice = findViewById<TextView>(R.id.tvPrice)
        var ivImage = findViewById<ImageView>(R.id.ivImage)
    }
}
