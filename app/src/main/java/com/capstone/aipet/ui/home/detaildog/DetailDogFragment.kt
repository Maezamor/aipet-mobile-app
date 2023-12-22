package com.capstone.aipet.ui.home.detaildog

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.capstone.aipet.R
import com.capstone.aipet.ViewModelFactory
import com.capstone.aipet.data.remote.DataResult
import com.capstone.aipet.databinding.FragmentDetailDogBinding
import com.capstone.aipet.ui.maps.MapsDetailFragment

class DetailDogFragment : Fragment() {



    private var _binding: FragmentDetailDogBinding? = null
    private val binding get() = _binding!!
    private lateinit var dogName: String
    private var dogId: Int? = null
    private  var dogAvatar: String = ""
    private  var dogStory: String = ""
    private var dogShelter: Int? = null
    private lateinit var  dogAge: String
    private var dogSteril: Int? = null
    private lateinit var detailMapsFragment: MapsDetailFragment
    private val viewModel: DetailDogViewModel by viewModels {
        ViewModelFactory(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailDogBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            dogName = it.getString(EXTRA_NAME, "")
            dogId = it.getInt(EXTRA_ID, 0)
            dogAvatar = it.getString(EXTRA_AVATAR, "")
            dogShelter = it.getInt(EXTRA_SHELTER, 0)
            dogAge = it.getString(EXTRA_AGE, "")
            dogSteril = it.getInt(EXTRA_STERIL, 0)
        }
        viewModel.getDetailDogById(dogId ?: 0)
        viewModel.detailDog.observe(viewLifecycleOwner) { response ->

            val type = response.data?.type?.activityLevel
            val steril = response.data?.gender?.name
            val selter = response.data?.selter?.name
            dogStory = response.data?.dog?.rescueStory!!
            val imageSelter = response.data?.selter?.picture
            val let = response.data?.selter?.let
            val lon = response.data?.selter?.lon
            val phoneShelter = response.data?.selter?.phone
            Log.d("data let and lon in detail dog","${let} and ${lon}")


            val bundle = Bundle()
            bundle.putDouble(MapsDetailFragment.EXTRA_LAT, let!!)
            bundle.putDouble(MapsDetailFragment.EXTRA_LONG, lon!!)
            bundle.putString(MapsDetailFragment.EXTRA_SHELTER, selter)
            bundle.putString(MapsDetailFragment.EXTRA_AVATAR, imageSelter)
            bundle.putString(MapsDetailFragment.EXTRA_PHONE, phoneShelter)

            binding.sterilizationText.text = steril
            binding.breedText.text = type
            binding.nameDogView.text = dogName
            binding.textRescuestory.text = dogStory
            binding.ageText.text = "${dogAge} Year"
            binding.selterText.text = selter

            detailMapsFragment = MapsDetailFragment()
            detailMapsFragment.arguments = bundle
            binding.btnMaps.setOnClickListener{
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.nav_host_fragment_activity_dashboard, detailMapsFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }


        }

        Glide.with(this)
            .load(dogAvatar)
            .skipMemoryCache(true)
            .into(binding.dtImage)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Kembali ke HomeFragment saat tombol back ditekan
                parentFragmentManager.popBackStack()
            }
        })
        binding.dtBack.setOnClickListener{
            requireActivity().onBackPressed()
        }
        binding.btnAddopt.setOnClickListener {
                checkoutDog(dogId)
            viewModel.checkoutDog(dogId!!)
        }

    }
    private fun checkoutDog(dogId: Int?) {

        // Panggil metode checkoutDogs dari repository
        viewModel.checkoutResult.observe(viewLifecycleOwner) { response ->
            when (response) {
                is DataResult.Loading -> {
                    showLoading(true)
                }
                is DataResult.Success -> {
                    showLoading(false)
                    showToast("Checkout berhasil!")
                    parentFragmentManager.popBackStack()
                }
                is DataResult.Error -> {
                    showLoading(false)
                    showToast("Checkout gagal: ${response.error}")
                }

                else -> {}
            }
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
    private fun showLoading(state: Boolean){
        binding.progressBar.isVisible = state
    }

    companion object{
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_AVATAR = "extra_avatar"
        const val EXTRA_STORY = "extra_story"
        const val EXTRA_SHELTER = "extra_shelter"
        const val EXTRA_AGE = "extra_age"
        const val EXTRA_STERIL = "extra_steril"
    }

}