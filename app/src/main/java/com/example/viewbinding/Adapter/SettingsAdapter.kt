package com.example.viewbinding.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.viewbinding.databinding.ItemSettingBinding

class SettingsAdapter(private var settingsList: List<String>) : RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val binding = ItemSettingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SettingsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        holder.bind(settingsList[position])
    }

    override fun getItemCount(): Int = settingsList.size


    fun updateSettings(newSettingsList: List<String>) {
        settingsList = newSettingsList
        notifyDataSetChanged()
    }

    class SettingsViewHolder(private val binding: ItemSettingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(setting: String) {
            binding.textView.text = setting
        }
    }
}
