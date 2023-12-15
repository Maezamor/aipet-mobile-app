package com.capstone.aipet.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.capstone.aipet.data.remote.response.history.ItemHistory
import com.capstone.aipet.databinding.ItemHistoryBinding

class ItemHistoryAdapter(private val callback: (history: ItemHistory) -> Unit) : RecyclerView.Adapter<HistoryViewHolder>() {

    private var historyList: List<ItemHistory> = listOf()

    fun submitList(newList: List<ItemHistory>) {
        historyList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = historyList[position]

        holder.view.root.setOnClickListener {
            Log.d("ItemDogAdapter", "Data pada posisi $position: $item")
            callback.invoke(item)
        }

        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }
}

class HistoryViewHolder(val view: ItemHistoryBinding) : RecyclerView.ViewHolder(view.root) {
    fun bind(item: ItemHistory) {
        view.rvName.text = item.name
        view.rvGender.text = item.gender
        view.rvCreated.text = item.createdAt
        view.rvAge.text = "${item.age} Year"

        // Gambar menggunakan Glide atau library gambar lainnya
        Glide.with(itemView.context)
            .load("https://storage.googleapis.com/aipet-storage/dog-image/ivana-la-tycZhR54Ddk-unsplash.jpg")
            .into(view.rvImage)
    }
}