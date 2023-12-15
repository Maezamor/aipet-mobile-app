package com.capstone.aipet.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.capstone.aipet.data.remote.response.service.ItemServices
import com.capstone.aipet.databinding.ItemPetcareBinding

class ItemServiceAdapter  (private val callback: (services: ItemServices) -> Unit)
: PagingDataAdapter<ItemServices, ServicesViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder {
        Log.d("ItemDogAdapter", "onCreateViewHolder called")
        val view = ItemPetcareBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ServicesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServicesViewHolder, position: Int) {
        val item = getItem(position)

        holder.view.root.setOnClickListener{
            if (item != null) {
                Log.d("ItemServiceAdapter", "Data pada posisi $position: $item")
                callback(item)
            }else {
                Log.e("ItemServiceAdapter", "Item pada posisi $position adalah null.")
            }
        }
        if (item != null){
            holder.bind(item)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemServices>() {
            override fun areItemsTheSame(oldItem: ItemServices, newItem: ItemServices): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ItemServices, newItem: ItemServices): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

}

class ServicesViewHolder(val view: ItemPetcareBinding): RecyclerView.ViewHolder(view.root) {
    fun bind(item: ItemServices) {
        view.rvPetcare.text = item.name
        view.rvCity.text = item.city
        val drawable = CircularProgressDrawable(view.root.context)
        drawable.strokeWidth = 5f
        drawable.centerRadius = 30f
        drawable.start()

        Glide.with(itemView.context)
            .load("https://storage.googleapis.com/aipet-storage/dog-image/34..03.jpg")
            .placeholder(drawable)
            .into(view.rvImage)

    }
}
