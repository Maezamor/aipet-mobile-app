package com.capstone.aipet.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.capstone.aipet.R
import com.capstone.aipet.data.DummyDataDog
import com.capstone.aipet.databinding.ItemDogBinding
import com.capstone.aipet.ui.home.detaildog.DetailDogFragment


class ItemDogAdapter(
    private val listShirt: ArrayList<DummyDataDog>) : RecyclerView.Adapter<ItemDogAdapter.ListViewHolder>() {
    class ListViewHolder(val binding: ItemDogBinding) :  RecyclerView.ViewHolder(binding.root) {
        val photo: ImageView = binding.rvImage
        val name: TextView = binding.rvName
        val gender: TextView = binding.rvGender
        val age: TextView = binding.rvAge
        val breed: TextView = binding.rvBreed
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDogBinding.inflate(inflater, parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, breed, gender, age, photo) = listShirt[position]
        holder.photo.setImageResource(photo)
        holder.name.text = name
        holder.breed.text = breed
        holder.gender.text = gender
        holder.age.text = age
        holder.itemView.setOnClickListener { v ->
            val activity = v!!.context as AppCompatActivity
            val detailDogFragment = DetailDogFragment()
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_activity_dashboard, detailDogFragment)
                .addToBackStack(null)
                .commit()
        }
    }
    override fun getItemCount(): Int = listShirt.size
}