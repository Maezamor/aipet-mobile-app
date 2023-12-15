package com.capstone.aipet.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.aipet.data.remote.response.rescue.ItemRescue
import com.capstone.aipet.databinding.ItemMatchBinding

class MatchAdapter : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    private var rescueItem: ItemRescue? = null

    inner class MatchViewHolder(private val binding: ItemMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(rescue: ItemRescue) {
            binding.rvRescueStory.text = rescue.rescueStory
        }
    }

    // Menambahkan fungsi untuk mengupdate data dalam adapter
    fun updateData(rescue: ItemRescue) {
        rescueItem = rescue
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val binding = ItemMatchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        rescueItem?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return if (rescueItem != null) 1 else 0
    }
}