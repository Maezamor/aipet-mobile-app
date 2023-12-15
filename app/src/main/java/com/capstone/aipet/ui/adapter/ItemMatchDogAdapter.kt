package com.capstone.aipet.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.capstone.aipet.data.remote.response.dogs.ItemDogs
import com.capstone.aipet.databinding.ItemDogBinding
import com.capstone.aipet.databinding.ItemMatchDogBinding

class ItemMatchDogAdapter  (private val callback: (dogs: ItemDogs) -> Unit)
: PagingDataAdapter<ItemDogs, MatchDogsViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchDogsViewHolder {
        Log.d("ItemDogAdapter", "onCreateViewHolder called")
        val view = ItemMatchDogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchDogsViewHolder(view)
    }
    override fun onBindViewHolder(holder: MatchDogsViewHolder, position: Int) {
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

class MatchDogsViewHolder(val view: ItemMatchDogBinding): RecyclerView.ViewHolder(view.root) {
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
            .load("https://storage.googleapis.com/aipet-storage/dog-image/ivana-la-tycZhR54Ddk-unsplash.jpg")
            .placeholder(drawable)
            .into(view.rvImage)

    }
}
