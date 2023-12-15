package com.capstone.aipet.ui.onboarding.personality.filter

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.aipet.R
import com.capstone.aipet.ViewModelFactory
import com.capstone.aipet.customview.button.ButtonOnBoarding
import com.capstone.aipet.data.filterData.age.AgeData
import com.capstone.aipet.data.filterData.groub.GroubData
import com.capstone.aipet.data.filterData.sterilization.SterilizationData
import com.capstone.aipet.data.remote.DataResult
import com.capstone.aipet.databinding.FragmentFilterOnBoardingBinding
import com.capstone.aipet.ui.adapter.ItemAgeAdapter
import com.capstone.aipet.ui.adapter.ItemGroubAdapter
import com.capstone.aipet.ui.adapter.ItemSterilizationAdapter
import com.capstone.aipet.ui.onboarding.personality.match.MatchmakingFragment

class FilterOnBoardingFragment : Fragment() {

    private lateinit var buttonNext: ButtonOnBoarding
    private var _binding: FragmentFilterOnBoardingBinding? = null
    private val viewModel: FilterOnBoardingViewModel by viewModels{
        ViewModelFactory(requireActivity())
    }
    private val binding get() = _binding!!
    private val ageList = AgeData.dataAge
    private val sterilizationList = SterilizationData.dataSterilization
    private val groubList = GroubData.dataGroub
    private var isAgeSelected: String = ""
    private var isSterilizationSelected: String = ""
    private var isGroubSelected: String = ""
    private var sterilCheked: Boolean = false
    private var ageCheked: Boolean = false
    private var groubCheked: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilterOnBoardingBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        buttonNext = binding.btnOnboarding
        setUpAdapter()
        setupAction()
//        onTestClick()
        setButtonOnBoardingEnable()
    }

    private fun setUpAdapter(){
        val ageAdapter = ItemAgeAdapter(ageList) { selectedText ->
            Log.d("FilterOnBoardingFragment", "Age List Size: ${ageList.size}")
            Toast.makeText(requireContext(), "Age selected : $selectedText", Toast.LENGTH_SHORT).show()
            isAgeSelected = selectedText
            ageCheked = true
            setButtonOnBoardingEnable()
        }

        val sterilizationAdapter = ItemSterilizationAdapter(sterilizationList) { selectedText ->
            Log.d("FilterOnBoardingFragment", "Sterilization List Size: ${sterilizationList.size}")
            Toast.makeText(requireContext(), "sterilization selected : $selectedText", Toast.LENGTH_SHORT).show()
            isSterilizationSelected = selectedText
            sterilCheked = true
            setButtonOnBoardingEnable()
        }
        val groubAdapter = ItemGroubAdapter(groubList) { selectedText ->
            Log.d("FilterOnBoardingFragment", "Sterilization List Size: ${groubList.size}")
            Toast.makeText(requireContext(), "Groub selected : $selectedText", Toast.LENGTH_SHORT).show()
            isGroubSelected = selectedText
            groubCheked = true
            setButtonOnBoardingEnable()
        }

        binding.rvListGroub.adapter = groubAdapter
        binding.rvListGroub.layoutManager = GridLayoutManager(requireContext(),2)
        binding.rvListAge.adapter = ageAdapter
        binding.rvListAge.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvListSterilization.adapter = sterilizationAdapter
        binding.rvListSterilization.layoutManager = LinearLayoutManager(requireContext())
    }
    private fun setButtonOnBoardingEnable() {
        val isSelectionComplete = ageCheked &&  sterilCheked && groubCheked
        buttonNext.isEnabled = isSelectionComplete
    }
    private fun setupAction() {
        buttonNext.setOnClickListener{
            if (isAgeSelected != null && isSterilizationSelected != null && isGroubSelected != null) {
                Log.d("checked nullabel" ,isAgeSelected )
                Log.d("checked nullabel" ,isSterilizationSelected )
                Log.d("checked nullabel" ,isGroubSelected )
                viewModel.onBoarding1(isAgeSelected, isSterilizationSelected, isGroubSelected)
                    .observe(viewLifecycleOwner) { DataResult ->
                        if (DataResult != null) {
                            when (DataResult) {
                                is DataResult.Loading -> {
                                    showLoading(true)
                                }

                                is DataResult.Success -> {
                                    showLoading(false)
                                    Toast.makeText(requireContext(), "success", Toast.LENGTH_LONG)
                                        .show()
                                    val matchmakingFragment = MatchmakingFragment()
                                    val fragmentManager = parentFragmentManager
                                    fragmentManager.beginTransaction().apply {
                                        replace(
                                            R.id.frame_container,
                                            matchmakingFragment,
                                            MatchmakingFragment::class.java.simpleName
                                        )
                                        addToBackStack(null)
                                        commit()
                                    }
                                }

                                is DataResult.Error -> {
                                    showLoading(false)
                                    Toast.makeText(
                                        requireContext(),
                                        "error in ${DataResult.error}",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    Log.d(
                                        "FilterOnBoardingFragment",
                                        "error from : ${DataResult.error}"
                                    )
                                }
                            }
                        }
                    }
            }else {
                Toast.makeText(
                    requireContext(),
                    "Pilih nilai untuk semua opsi",
                    Toast.LENGTH_SHORT
                ).show()
            }
            }
        }
//    private fun onTestClick(){
//        binding.btnTest.setOnClickListener{
//        val matchmakingFragment = MatchmakingFragment()
//        val fragmentManager = parentFragmentManager
//        fragmentManager.beginTransaction().apply {
//            replace(
//                R.id.frame_container,
//                matchmakingFragment,
//                MatchmakingFragment::class.java.simpleName
//            )
//            addToBackStack(null)
//            commit()
//        }
//        }
//    }
    private fun showLoading(state: Boolean){
        binding.progressBar.isVisible = state

    }

}