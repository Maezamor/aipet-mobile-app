package com.capstone.aipet.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.aipet.data.filterData.sterilization.Sterilization
import com.capstone.aipet.databinding.ItemSterilizationBinding

class ItemSterilizationAdapter (private val radioSteril: List<Sterilization>,
                                private val onItemChecked: (String) -> Unit
):  RecyclerView.Adapter<ItemSterilizationAdapter.ViewHolder>() {
    private var selectedPosition = RecyclerView.NO_POSITION
    inner class ViewHolder(private val binding: ItemSterilizationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(radioSteril: Sterilization, position : Int) {
            binding.rbSterilization.text = radioSteril.sterilization
            binding.rbSterilization.isChecked = position == selectedPosition
            binding.rbSterilization.setOnClickListener {
                if (position != selectedPosition) {
                    selectedPosition = position
                    notifyDataSetChanged()
                    onItemChecked(radioSteril.sterilization)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemSterilizationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(radioSteril[position], position)
    }

    override fun getItemCount(): Int = radioSteril.size
}