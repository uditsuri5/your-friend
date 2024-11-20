package com.example.homescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homescreen.appointmentsRV.Appointments
import com.example.homescreen.appointmentsRV.AppointmentsAdapter
import com.example.homescreen.databinding.SecondFragmentBinding

class SecondFragment : Fragment(R.layout.second_fragment) {
    private lateinit var adapter : AppointmentsAdapter
    private lateinit var binding : SecondFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SecondFragmentBinding.inflate(inflater, container, false)
        var rvAppointments = binding.rvAppointments
        var AppointmnetsList = mutableListOf(
            Appointments("Dr. Piyush Kumar", "Psychiatrist", "15/11/24", "12:00 pm",R.drawable.doc_icon),
            Appointments("Dr. Anas Rana", "Psychologist", "15/11/24", "10:00 am",R.drawable.doc_icon),
            Appointments("Dr. Udit Suri", "Therapist", "16/11/24", "8:00 pm",R.drawable.doc_icon),
            Appointments("Dr. Jigyansu", "Counselor", "21/11/24", "5:00 pm",R.drawable.doc_icon),
            Appointments("Dr. Laxmi", "Psychoanalyst", "23/11/24", "11:00 am",R.drawable.doc_icon),

            )


        val adapter = AppointmentsAdapter(AppointmnetsList, requireContext())
        rvAppointments.adapter = adapter
        rvAppointments.layoutManager = LinearLayoutManager(context)

        return binding.root


        }
    }





