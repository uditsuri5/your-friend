package com.example.homescreen.workshopsRV

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homescreen.R

class WorkshopsAdapter(
    var workshops: MutableList<Workshops>
) : RecyclerView.Adapter<WorkshopsAdapter.WorkshopsViewHolder>(){
    inner class WorkshopsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkshopsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_workshops, parent, false)
        return WorkshopsViewHolder(view)
    }

    @SuppressLint("WrongViewCast")
    override fun onBindViewHolder(holder: WorkshopsViewHolder, position: Int) {
        holder.itemView.apply {
            var tvName = findViewById<TextView>(R.id.tvName)
            val tvDoctor = findViewById<TextView>(R.id.tvDoctor)
            val tvPrice = findViewById<TextView>(R.id.tvPrice)
            val tvDate = findViewById<TextView>(R.id.tvDate)
            val tvTime = findViewById<TextView>(R.id.tvTime)
            var ivImage = findViewById<ImageView>(R.id.ivImage)
            tvName.text = workshops[position].name
            tvDoctor.text = workshops[position].doctor
            tvPrice.text = workshops[position].price
            tvDate.text = workshops[position].date
            tvTime.text = workshops[position].time
            ivImage.setImageResource(workshops[position].image)
        }

    }


    override fun getItemCount(): Int {
        return workshops.size
    }
}