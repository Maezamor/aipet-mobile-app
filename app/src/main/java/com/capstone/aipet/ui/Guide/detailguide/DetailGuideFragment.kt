package com.capstone.aipet.ui.Guide.detailguide

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.capstone.aipet.R
import com.capstone.aipet.data.guideData.GuideData
import com.capstone.aipet.databinding.FragmentDetailGuideBinding
import com.capstone.aipet.databinding.FragmentHomeGuideBinding

class DetailGuideFragment : Fragment() {

    companion object {
        fun newInstance() = DetailGuideFragment()
    }

    private var _binding: FragmentDetailGuideBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: DetailGuideViewModel
    private var guideId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            guideId = it.getInt("guideId", 0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailGuideBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailGuideViewModel::class.java)
        val guide = GuideData.dataguide.find {
            it.id == guideId
        }

        binding.titleGuide.text = guide?.title
        binding.subtitleGuide.text = guide?.subtitle
        binding.descGuide.text = guide?.description
        binding.dtBack.setOnClickListener{
            requireActivity().onBackPressed()
        }
    }

}