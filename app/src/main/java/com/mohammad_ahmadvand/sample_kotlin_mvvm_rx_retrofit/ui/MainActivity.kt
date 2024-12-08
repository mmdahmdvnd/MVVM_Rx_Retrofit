package com.mohammad_ahmadvand.sample_kotlin_mvvm_rx_retrofit.ui

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mohammad_ahmadvand.sample_kotlin_mvvm_rx_retrofit.R
import com.mohammad_ahmadvand.sample_kotlin_mvvm_rx_retrofit.data.network.RetrofitInstance
import com.mohammad_ahmadvand.sample_kotlin_mvvm_rx_retrofit.data.repository.UserRepository

class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ایجاد ApiService و UserRepository
        val apiService = RetrofitInstance.api
        val userRepository = UserRepository(apiService)

        // استفاده از UserViewModelFactory
        val factory = UserViewModelFactory(userRepository)
        userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)

        // حالا می‌توانید با userViewModel کار کنید
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