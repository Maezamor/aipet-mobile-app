package com.capstone.aipet.ui.Guide.homeguide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.aipet.R
import com.capstone.aipet.data.guideData.Guide
import com.capstone.aipet.data.guideData.GuideData
import com.capstone.aipet.databinding.FragmentHomeGuideBinding
import com.capstone.aipet.ui.Guide.detailguide.DetailGuideFragment
import com.capstone.aipet.ui.adapter.ItemGuideAdapter


class HomeGuideFragment : Fragment() {

    private var _binding: FragmentHomeGuideBinding? = null
    private val binding get() = _binding!!
    private val itemGuideAdapter = ItemGuideAdapter(GuideData.dataguide)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeGuideBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.rvListGuide.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = itemGuideAdapter
            itemGuideAdapter.setOnItemClickListener(object : ItemGuideAdapter.OnItemClickListener {
                override fun onItemClick(guide: Guide) {
                    val fragment = DetailGuideFragment()

                    val bundle = Bundle()
                    bundle.putInt("guideId", guide.id)
                    fragment.arguments = bundle

                    val transaction =
                        (requireActivity() as AppCompatActivity).supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.frame_container, fragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
            })
            binding.dtBack.setOnClickListener{
                requireActivity().onBackPressed()
            }



        }


    }


}