package com.example.viewbinding

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.viewbinding.Adapter.CardAdapter
import com.example.viewbinding.Group.AboutActivity
import com.example.viewbinding.Group.HelpActivity
import com.example.viewbinding.Group.LogoutActivity
import com.example.viewbinding.Group.NotificationsActivity
import com.example.viewbinding.Group.ProfileActivity
import com.example.viewbinding.Group.SettingsActivity
import com.example.viewbinding.Item.CardItem
import com.example.viewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CardAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val rootView = findViewById<View>(R.id.containerCL)
        rootView.setBackgroundResource(R.drawable.gradient)
        val cardItems = listOf(
            CardItem("Профиль", "Посмотреть и редактировать свой профиль"),
            CardItem("Настройки", "Изменить настройки приложения"),
            CardItem("Уведомления", "Просмотреть свои уведомления"),
            CardItem("О приложении", "Узнать информацию о приложении"),
            CardItem("Помощь", "Получить помощь и поддержку"),
            CardItem("Выход", "Выйти из своего аккаунта")
        )

        val cardAdapter = CardAdapter(cardItems, this)
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = cardAdapter
    }

    override fun onItemClick(item: CardItem) {
        openActivity(item.title)
    }

    private fun openActivity(title: String) {
        val intent = when (title) {
            "Профиль" -> Intent(this, ProfileActivity::class.java)
            "Настройки" -> Intent(this, SettingsActivity::class.java)
            "Уведомления" -> Intent(this, NotificationsActivity::class.java)
            "О приложении" -> Intent(this, AboutActivity::class.java)
            "Помощь" -> Intent(this, HelpActivity::class.java)
            "Выход" -> Intent(this, LogoutActivity::class.java)
            else -> return
        }
        startActivity(intent)
    }
}