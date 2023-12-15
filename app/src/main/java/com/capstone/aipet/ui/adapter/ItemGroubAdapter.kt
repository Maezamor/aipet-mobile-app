package com.capstone.aipet.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.aipet.data.filterData.groub.Groub
import com.capstone.aipet.databinding.ItemGroubBinding

class ItemGroubAdapter(private val radioGroub: List<Groub>,
                        private val onItemChecked: (String) -> Unit
):  RecyclerView.Adapter<ItemGroubAdapter.ViewHolder>() {
    private var selectedPosition = RecyclerView.NO_POSITION
    inner class ViewHolder(private val binding: ItemGroubBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(radioGroub: Groub, position: Int) {
            binding.tvGroup.text = radioGroub.groub
            binding.rbGroub.isChecked = position == selectedPosition
            binding.rbGroub.setOnClickListener {
                if (position != selectedPosition) {
                    selectedPosition = position
                    notifyDataSetChanged()
                    onItemChecked(radioGroub.groub)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemGroubBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(radioGroub[position], position)
    }

    override fun getItemCount(): Int = radioGroub.size
}