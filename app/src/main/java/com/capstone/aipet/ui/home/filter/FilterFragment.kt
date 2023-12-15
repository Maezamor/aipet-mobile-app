package com.capstone.aipet.ui.home.filter

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.capstone.aipet.R
import com.capstone.aipet.databinding.FragmentDetailDogBinding
import com.capstone.aipet.databinding.FragmentFilterBinding

class FilterFragment : Fragment() {

    companion object {
        fun newInstance() = FilterFragment()
    }

    private lateinit var viewModel: FilterViewModel
    private lateinit var binding: FragmentFilterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_filter, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FilterViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
