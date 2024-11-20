package com.example.homescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homescreen.databinding.ThirdFragmentBinding
import com.example.homescreen.workshopsRV.Workshops
import com.example.homescreen.workshopsRV.WorkshopsAdapter

class ThirdFragment : Fragment(R.layout.third_fragment) {

    private lateinit var adapter : WorkshopsAdapter
    private lateinit var binding : ThirdFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ThirdFragmentBinding.inflate(inflater, container, false)
        var rvWorkshops = binding.rvWorkshops
        var WorkshopsList = mutableListOf(
            Workshops("Mental Health","By : Dr. Sekhar" ,"15/11/24", "11:00 am", "Rs.15000", R.drawable.workshop_icon),
            Workshops("Depression", "By : Dr. Venkat","16/11/24", "11:00 am", "Rs.10500", R.drawable.workshop_icon),
            Workshops("Child Health", "By : Dr. Vibhakar","18/11/24", "11:00 am", "Rs.16000", R.drawable.workshop_icon),
            Workshops("Anxiety Attacks", "By : Dr. Divanshi","20/11/24", "11:00 am", "Rs.25000", R.drawable.workshop_icon),
            Workshops("Old Age", "By : Dr. MS Dhoni","27/11/24", "11:00 am", "Rs.15000", R.drawable.workshop_icon),
            Workshops("Work Life", "By : Dr. Virat Kohli","3/12/24", "11:00 am", "Rs.32000", R.drawable.workshop_icon)

            )


        val adapter = WorkshopsAdapter(WorkshopsList)
        rvWorkshops.adapter = adapter
        rvWorkshops.layoutManager = LinearLayoutManager(context)

        return binding.root


    }
}