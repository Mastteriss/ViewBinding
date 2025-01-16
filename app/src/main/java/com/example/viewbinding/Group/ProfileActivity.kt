package com.example.viewbinding.Group

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.viewbinding.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var sharedPreferences: SharedPreferences
    private val PICK_IMAGE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE)

        loadProfileInfo()

        binding.saveButton.setOnClickListener {
            saveProfileInfo()
        }

        binding.profileImage.setOnClickListener {
            openGallery()
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            val selectedImageUri = data.data
            binding.profileImage.setImageURI(selectedImageUri)
        }
    }

    private fun saveProfileInfo() {
        val name = binding.nameInput.text.toString()
        val editor = sharedPreferences.edit()
        editor.putString("userName", name)
        editor.apply()
        loadProfileInfo()
    }

    private fun loadProfileInfo() {
        val savedName = sharedPreferences.getString("userName", "Имя не установлено")
        binding.profileInfo.text = "Имя: $savedName"
    }
}