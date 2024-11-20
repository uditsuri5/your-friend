package com.example.homescreen.doctorsRV

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homescreen.R

class DoctorsAdapter(
    var doctors: MutableList<Doctors>
) : RecyclerView.Adapter<DoctorsAdapter.DoctorsViewHolder>() {
    inner class DoctorsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_doctors, parent, false)
        return DoctorsViewHolder(view)
    }

    @SuppressLint("WrongViewCast")
    override fun onBindViewHolder(holder: DoctorsViewHolder, position: Int) {
        holder.itemView.apply {
            var tvName = findViewById<TextView>(R.id.tvName)
            var tvSpeciality = findViewById<TextView>(R.id.tvSpeciality)
            var tvExperience = findViewById<TextView>(R.id.tvExperience)
            var tvDegree = findViewById<TextView>(R.id.tvDegree)
            var tvPrice = findViewById<TextView>(R.id.tvPrice)
            var ivImage = findViewById<ImageView>(R.id.ivImage)
            tvName.text = doctors[position].name
            tvSpeciality.text = doctors[position].speciality
            tvExperience.text = doctors[position].experience
            tvDegree.text = doctors[position].degree
            tvPrice.text = doctors[position].price
            ivImage.setImageResource(doctors[position].image)
        }

    }


    override fun getItemCount(): Int {
        return doctors.size
    }
}