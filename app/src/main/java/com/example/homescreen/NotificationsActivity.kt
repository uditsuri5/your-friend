package com.example.homescreen

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class NotificationsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notifications)
        supportActionBar?.title = "Notifications"
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#219EBC")))
    }
}