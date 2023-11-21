package com.capstone.aipet.ui.home.detaildog

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.capstone.aipet.R
import com.capstone.aipet.databinding.FragmentDetailDogBinding

class DetailDogFragment : Fragment() {



    private lateinit var viewModel: DetailDogViewModel
    private lateinit var  binding: FragmentDetailDogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_dog, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailDogViewModel::class.java)

    }

}