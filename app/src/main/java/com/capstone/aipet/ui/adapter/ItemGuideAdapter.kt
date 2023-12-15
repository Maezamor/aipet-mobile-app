package com.capstone.aipet.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.capstone.aipet.R
import com.capstone.aipet.data.guideData.Guide
import com.capstone.aipet.databinding.CartGuideBinding
import com.capstone.aipet.ui.Guide.detailguide.DetailGuideFragment

class ItemGuideAdapter(private val guides: List<Guide>) :
    RecyclerView.Adapter<ItemGuideAdapter.GuideViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(guide: Guide)
    }
    private var itemClickListener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.itemClickListener = listener
    }

    inner class GuideViewHolder(private val binding: CartGuideBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(guide: Guide) {
            binding.rvImage.setImageResource(guide.imageResId)
            binding.rvTitle.text = guide.title
            binding.rvSubtitle.text = guide.subtitle
            binding.root.setOnClickListener {
                itemClickListener?.onItemClick(guide)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuideViewHolder {
        val binding =
            CartGuideBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuideViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GuideViewHolder, position: Int) {
        holder.bind(guides[position])
    }

    override fun getItemCount(): Int {
        return guides.size
    }
}