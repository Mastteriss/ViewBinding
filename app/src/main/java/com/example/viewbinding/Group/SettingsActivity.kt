package com.example.viewbinding.Group

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.example.viewbinding.Adapter.SettingsAdapter
import com.example.viewbinding.databinding.ActivitySettingsBinding


class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var settingsAdapter: SettingsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val themeInput: TextView = binding.themeInput
        val notificationsEmailInput: EditText = binding.notificationsEmailInput
        val saveSettingsButton: Button = binding.saveSettingsButton
        val settingsRecyclerView: RecyclerView = binding.settingsRecyclerView


        sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE)


        settingsRecyclerView.layoutManager = LinearLayoutManager(this)


        val settingsList = loadSettings()
        settingsAdapter = SettingsAdapter(settingsList)
        settingsRecyclerView.adapter = settingsAdapter


        saveSettingsButton.setOnClickListener {
            val theme = themeInput.text.toString()
            val notificationsEmail = notificationsEmailInput.text.toString()


            saveSettings(theme, notificationsEmail)


            settingsAdapter.updateSettings(loadSettings())
            showToast("Настройки сохранены")
        }
    }


    private fun saveSettings(theme: String, notificationsEmail: String) {
        val editor = sharedPreferences.edit()
        editor.putString("theme", theme)
        editor.putString("notificationsEmail", notificationsEmail)
        editor.apply()
    }


    private fun loadSettings(): List<String> {
        val theme = sharedPreferences.getString("theme", "Тема по умолчанию")
        val notificationsEmail = sharedPreferences.getString("notificationsEmail", "Нет email")
        return listOf("Тема: $theme", "Email для уведомлений: $notificationsEmail")
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}