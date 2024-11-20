package com.example.homescreen.appointmentsRV

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homescreen.R
import com.example.homescreen.appointmentsRV.AppointmentPageActivity
import com.google.android.material.button.MaterialButton

class AppointmentsAdapter(
    var appointments: MutableList<Appointments>,
    val context: Context // Pass context to use it for starting the activity
) : RecyclerView.Adapter<AppointmentsAdapter.AppointmentsViewHolder>() {

    inner class AppointmentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_appointments, parent, false)
        return AppointmentsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppointmentsViewHolder, position: Int) {
        holder.itemView.apply {
            val tvName = findViewById<TextView>(R.id.tvName)
            val tvSpeciality = findViewById<TextView>(R.id.tvSpeciality)
            val tvDate = findViewById<TextView>(R.id.tvDate)
            val tvTime = findViewById<TextView>(R.id.tvTime)
            val ivImage = findViewById<ImageView>(R.id.ivImage)
            val btnPresecription = findViewById<MaterialButton>(R.id.btnPresecription)

            // Set the text and image for the appointment
            tvName.text = appointments[position].name
            tvSpeciality.text = appointments[position].speciality
            tvDate.text = appointments[position].date
            tvTime.text = appointments[position].time
            ivImage.setImageResource(appointments[position].image)

            // Handle the click on the Book Appointment button
            btnPresecription.setOnClickListener {
                // Create an intent to navigate to the appointment page
                val intent = Intent(context, AppointmentPageActivity::class.java)
                // You can pass additional data if needed (e.g. appointment details)
                // intent.putExtra("appointmentId", appointments[position].id)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return appointments.size
    }
}
