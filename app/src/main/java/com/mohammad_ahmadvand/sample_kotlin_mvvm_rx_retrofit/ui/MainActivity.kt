package com.mohammad_ahmadvand.sample_kotlin_mvvm_rx_retrofit.ui

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.mohammad_ahmadvand.sample_kotlin_mvvm_rx_retrofit.R

class MainActivity : AppCompatActivity() {
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userInfoTextView: TextView = findViewById(R.id.user_info)

        userViewModel.user.observe(this, Observer { user ->
            userInfoTextView.text = "ID: ${user.id}\nName: ${user.name}\nEmail: ${user.email}"
        })

        userViewModel.error.observe(this, Observer { errorMessage ->
            Toast.makeText(this, "Error: $errorMessage", Toast.LENGTH_LONG).show()
        })

        userViewModel.fetchUser()
    }
}