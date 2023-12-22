package com.capstone.aipet.ui.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.capstone.aipet.data.remote.response.dogs.ItemRecomend
import com.capstone.aipet.databinding.ItemMatchDogBinding

class ItemMatchDogAdapter(private val callback: (recomend: ItemRecomend) -> Unit) : RecyclerView.Adapter<MatchDogsViewHolder>() {

    private var recomendList: List<ItemRecomend?>? = listOf()

    fun submitList(newList: List<ItemRecomend?>?) {
        recomendList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchDogsViewHolder {
        val view = ItemMatchDogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchDogsViewHolder(view)
    }
    override fun onBindViewHolder(holder: MatchDogsViewHolder, position: Int) {
        val item = recomendList?.get(position)

        holder.view.root.setOnClickListener {
            Log.d("ItemMatchDogAdapter", "Data pada posisi $position: $item")
            callback.invoke(item!!)
        }
        holder.bind(item!!)
    }

    override fun getItemCount(): Int {
        return recomendList?.size ?: 0
    }
}
class MatchDogsViewHolder(val view: ItemMatchDogBinding): RecyclerView.ViewHolder(view.root) {
    fun bind(item: ItemRecomend) {
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
