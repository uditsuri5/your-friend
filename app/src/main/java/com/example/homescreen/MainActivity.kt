package com.example.homescreen

import ScrollingTextAdaptive
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homescreen.Authentication.Login
import com.example.homescreen.appointmentsRV.Prescription
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var buttonLoginLogout: Button
    private lateinit var fabAI: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var auth: FirebaseAuth
    private lateinit var adapter: ScrollingTextAdaptive
    private val handler = Handler(Looper.getMainLooper())
    private var scrollTimer: Timer? = null
    private var scrollPosition = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase Auth
        auth = Firebase.auth

        // Fragments
        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()
        val thirdFragment = ThirdFragment()

        // Login/Logout Button
        buttonLoginLogout = findViewById(R.id.buttonLoginLogout)
        updateButtonLabel()

        buttonLoginLogout.setOnClickListener {
            if (auth.currentUser != null) {
                // If logged in, log out the user
                auth.signOut()
                updateButtonLabel()
            } else {
                // If not logged in, redirect to Login activity
                startActivity(Intent(this, Login::class.java))
            }
        }

        // Floating Action Button for AI Activity
        fabAI = findViewById(R.id.flb_ai)
        fabAI.setOnClickListener {
            val intent = Intent(this, AIActivity::class.java)
            startActivity(intent)
        }

        // Set Default Fragment
        setCurrentFragment(firstFragment)

        // Bottom Navigation
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.miHome -> setCurrentFragment(firstFragment)
                R.id.miAppointment -> setCurrentFragment(secondFragment)
                R.id.miWorkshop -> setCurrentFragment(thirdFragment)
            }
            true
        }

        // Customize Action Bar
        supportActionBar?.title = "Your Friend"
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#219EBC")))

        // Initialize RecyclerView for scrolling text
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

//        val services = listOf(
//            "Therapy Sessions",
//            "Mindfulness Programs",
//            "Personal Growth Counseling",
//            "Anxiety & Stress Management",
//            "Couples Therapy",
//            "Cognitive Behavioral Therapy",
//            "Mental Health Check-Ups",
//            "Workplace Well-being Programs",
//            "Youth Mental Health Resources"
//        )
        val list= listOf(
            R.drawable.therapysession,
            R.drawable.mindfulness,
            R.drawable.image_stress,
            R.drawable.pysocologystress,
            R.drawable.personal_growth,

        )
        adapter = ScrollingTextAdaptive(list)
        recyclerView.adapter = adapter

        // Start automatic scrolling for the RecyclerView
        startAutoScrolling()
    }

    // Auto-scrolling for the RecyclerView
    private fun startAutoScrolling() {
        scrollTimer = Timer()
        scrollTimer?.schedule(object : TimerTask() {
            override fun run() {
                handler.post {
                    if (scrollPosition == adapter.itemCount) {
                        scrollPosition = 0
                    }
                    recyclerView.smoothScrollToPosition(scrollPosition++)
                }
            }
        }, 0, 2000) // Adjust the interval as needed (currently 2 seconds)
    }

    // Stop auto-scrolling when the activity is destroyed
    override fun onDestroy() {
        super.onDestroy()
        scrollTimer?.cancel()
        handler.removeCallbacksAndMessages(null)
    }

    // Set Current Fragment Function
    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
    }

    // Update the button label based on login state
    private fun updateButtonLabel() {
        if (auth.currentUser != null) {
            buttonLoginLogout.text = "Logout"
        } else {
            buttonLoginLogout.text = "Login"
        }
    }

    // Create App Bar Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

    // Handle App Bar Menu Item Clicks
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.miProfile -> Intent(this, ProfileActivity::class.java).also { startActivity(it) }
            R.id.miNotifications -> Intent(this, NotificationsActivity::class.java).also { startActivity(it) }
            R.id.miAppointments -> Intent(this, ProgressActivity::class.java).also { startActivity(it) }
        }
        return true
    }

    // Show Prescription Activity
    fun showPrescription(view: View) {
        Intent(this, Prescription::class.java).also { startActivity(it) }
    }
}
