package com.capstone.aipet.ui.profile

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.capstone.aipet.databinding.FragmentProfileBinding
import com.capstone.aipet.pref.UserPreference
import com.capstone.aipet.ui.Guide.GuideActivity
import com.capstone.aipet.ui.healthservice.PetCareActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        val sharedPreferences = UserPreference.init(requireContext(), "onSignIn")
        val userName = sharedPreferences.getString("username", "")
        val name = sharedPreferences.getString("name", "")
        val userAddress = sharedPreferences.getString("address", "")
        val userPhone = sharedPreferences.getString("phone", "")
        val userEmail = sharedPreferences.getString("email", "")
        binding.titleUsername.text = userName
        binding.tvFullname.text = name
        binding.tvUsername.text = userName
        binding.tvName.text = name
        binding.tvEmail.text = userEmail
        binding.tvPhone.text = userPhone
        binding.tvAddress.text = userAddress


        binding.btnGuide.setOnClickListener{
            val intent = Intent(requireContext(), GuideActivity::class.java)
            startActivity(intent)
        }
        binding.buttonHealthservice.setOnClickListener{
            val intent = Intent(requireContext(), PetCareActivity::class.java)
            startActivity(intent)
        }
    }

}