package com.capstone.aipet.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.capstone.aipet.data.remote.response.dogs.ItemDogs
import com.capstone.aipet.databinding.ItemDogBinding


class ItemDogAdapter(
    private val callback: (dogs: ItemDogs) -> Unit)
    : PagingDataAdapter<ItemDogs, DogsViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder {
        Log.d("ItemDogAdapter", "onCreateViewHolder called")
        val view = ItemDogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DogsViewHolder(view)
    }
    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
        val item = getItem(position)

        holder.view.root.setOnClickListener{
            if (item != null) {
                Log.d("ItemDogAdapter", "Data pada posisi $position: $item")
                callback.invoke(item)
            }else {
                Log.e("ItemDogAdapter", "Item pada posisi $position adalah null.")
            }
        }
        if (item != null){
            holder.bind(item)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemDogs>() {
            override fun areItemsTheSame(oldItem: ItemDogs, newItem: ItemDogs): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ItemDogs, newItem: ItemDogs): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

}

class DogsViewHolder(val view: ItemDogBinding): RecyclerView.ViewHolder(view.root) {
    fun bind(item: ItemDogs) {
        view.rvName.text = item.name
        view.rvGender.text = item.gender
        view.rvCaracter.text = item.character
        view.rvAge.text = "${item.age} Year"

        val drawable = CircularProgressDrawable(view.root.context)
        drawable.strokeWidth = 5f
        drawable.centerRadius = 30f
        drawable.start()

        Glide.with(itemView.context)
            .load(item.picture)
            .placeholder(drawable)
            .into(view.rvImage)

    }
}
