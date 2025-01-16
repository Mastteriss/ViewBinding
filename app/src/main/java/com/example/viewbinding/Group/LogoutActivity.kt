package com.example.viewbinding.Group

import android.os.Bundle
import com.example.viewbinding.databinding.ActivityLogoutBinding
import androidx.appcompat.app.AppCompatActivity


class LogoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.confirmLogoutButton.setOnClickListener {
            val sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE)
            sharedPreferences.edit().clear().apply()
            finishAffinity()
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}