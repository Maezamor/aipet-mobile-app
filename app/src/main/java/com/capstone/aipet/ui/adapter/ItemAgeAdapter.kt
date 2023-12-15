package com.capstone.aipet.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.aipet.data.filterData.age.Age
import com.capstone.aipet.databinding.ItemAgeBinding

class ItemAgeAdapter(private val radioAge: List<Age>,
                     private val onItemChecked: (String) -> Unit
):  RecyclerView.Adapter<ItemAgeAdapter.ViewHolder>() {
    private var selectedPosition = RecyclerView.NO_POSITION
    inner class ViewHolder(private val binding: ItemAgeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(radioAge: Age, position: Int) {
            binding.rbAge.text = "${radioAge.age} Year"
            binding.rbAge.isChecked = position == selectedPosition
            binding.rbAge.setOnClickListener {
                if (position != selectedPosition) {
                    selectedPosition = position
                    notifyDataSetChanged()
                    onItemChecked(radioAge.age)
                }
            }
        }
    }
    fun getSelectedAge(): String? {
        return if (selectedPosition != RecyclerView.NO_POSITION) {
            radioAge[selectedPosition].age
        } else {
            null
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemAgeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(radioAge[position], position)
    }

    override fun getItemCount(): Int = radioAge.size
}