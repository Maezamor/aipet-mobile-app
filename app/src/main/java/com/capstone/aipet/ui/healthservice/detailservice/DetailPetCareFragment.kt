package com.capstone.aipet.ui.healthservice.detailservice

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.capstone.aipet.R
import com.capstone.aipet.databinding.FragmentDetailPetCareBinding
import com.capstone.aipet.databinding.FragmentPetCareBinding
import com.capstone.aipet.ui.home.detaildog.DetailDogFragment

class DetailPetCareFragment : Fragment() {



    private lateinit var viewModel: DetailPetCareViewModel
    private var _binding: FragmentDetailPetCareBinding? = null
    private val binding get() = _binding!!

    private lateinit var serviceName: String
    private lateinit var serviceId: String
    private lateinit var serviceImage: String
    private lateinit var servicePhone: String
    private lateinit var serviceCity: String
    private lateinit var serviceDesc: String
    private lateinit var serviceAddress: String
    private lateinit var serviceSM1: String
    private lateinit var serviceSM2: String
    private lateinit var serviceSM3: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailPetCareBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailPetCareViewModel::class.java)
        arguments?.let {
            serviceName = it.getString(DetailPetCareFragment.EXTRA_NAME, "")
            serviceId = it.getString(DetailPetCareFragment.EXTRA_ID, "")
            serviceImage = it.getString(DetailPetCareFragment.EXTRA_AVATAR, "")
            serviceDesc = it.getString(DetailPetCareFragment.EXTRA_DESC, "")
            servicePhone = it.getString(DetailPetCareFragment.EXTRA_PHONE, "")
            serviceCity = it.getString(DetailPetCareFragment.EXTRA_CITY, "")
            serviceSM1 = it.getString(DetailPetCareFragment.EXTRA_SM1, "")
            serviceSM2 = it.getString(DetailPetCareFragment.EXTRA_SM2, "")
            serviceSM3 = it.getString(DetailPetCareFragment.EXTRA_SM3, "")
        }
        binding.textDescription.text = serviceDesc
        binding.namepetcareView.text = serviceName
        Glide.with(this)
            .load("https://storage.googleapis.com/aipet-storage/dog-image/34..03.jpg")
            .into(binding.dtImage)

        binding.imgWa.setOnClickListener{
            bukaLinkWeb("http://www.dicoding.com")
        }
    }
    fun bukaLinkWeb(linkWeb: String) {
        val uri = Uri.parse(linkWeb)
        val intent = Intent(Intent.ACTION_VIEW, uri)

        // Periksa apakah ada aplikasi yang dapat menangani Intent ini
        if (intent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(intent)
        } else {
            // Handle kasus jika tidak ada aplikasi yang dapat menangani Intent
            // Misalnya, tampilkan pesan kepada pengguna atau lakukan tindakan lain.
        }
    }
    companion object{
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_AVATAR = "extra_avatar"
        const val EXTRA_DESC = "extra_desc"
        const val EXTRA_CITY = "extra_city"
        const val EXTRA_PHONE = "extra_phone"
        const val EXTRA_SM1 = "extra_sm1"
        const val EXTRA_SM2 = "extra_sm2"
        const val EXTRA_SM3 = "extra_sm3"
    }
}