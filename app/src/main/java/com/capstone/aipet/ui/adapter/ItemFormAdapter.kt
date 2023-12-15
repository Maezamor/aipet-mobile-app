//package com.capstone.aipet.ui.adapter
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import android.widget.RadioButton
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.capstone.aipet.databinding.ItemFormPersonalityBinding
//import com.capstone.aipet.databinding.ItemMatchBinding
//import com.capstone.aipet.ui.onboarding.personality.form.FormPersonalityFragment
//import com.capstone.aipet.ui.onboarding.personality.ModelClass
//
//class ItemFormAdapter(
//    fragment: FormPersonalityFragment,
//    arrayList: ArrayList<ModelClass>
//) : RecyclerView.Adapter<ItemFormAdapter.ViewHolder>() {
//
//    private var context: Context
//    private var arraylist: ArrayList<ModelClass>
//    private var isNewRadioButtonisChecked = false
//    private var lastcheckedPosition = 0
//
//    init {
//        this.context = fragment.requireContext()
//        this.arraylist = arrayList
//    }
//
//    inner class ViewHolder(binding: ItemMatchBinding) : RecyclerView.ViewHolder(binding.root) {
//        val question: TextView = binding.question
//        val setuju: RadioButton = binding.rbSetuju
//
//        init {
//            setuju.setOnClickListener {
//                handleRadioButtonChecks(bindingAdapterPosition)
//            }
//        }
//
//        private fun handleRadioButtonChecks(adapterPosition: Int) {
//            if (adapterPosition != RecyclerView.NO_POSITION) {
//                isNewRadioButtonisChecked = true
//                arraylist[lastcheckedPosition].isSelected = false
//                arraylist[adapterPosition].isSelected = true
//                lastcheckedPosition = adapterPosition
//                notifyDataSetChanged()
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val binding = ItemFormPersonalityBinding.inflate(inflater, parent, false)
//        return ViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val modelClass = arraylist[position]
//        holder.question.text = modelClass.name
//
//        if (isNewRadioButtonisChecked) {
//            holder.setuju.isChecked = true
//            isNewRadioButtonisChecked = false
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return arraylist.size
//    }
//}