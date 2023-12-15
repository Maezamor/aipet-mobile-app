package com.capstone.aipet.ui.healthservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.capstone.aipet.R
import com.capstone.aipet.databinding.ActivityPetCareBinding
import com.capstone.aipet.ui.healthservice.service.PetCareFragment

class PetCareActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPetCareBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetCareBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragmentManager = supportFragmentManager
        val petCareFragment = PetCareFragment()
        val fragment = fragmentManager.findFragmentByTag(PetCareFragment::class.java.simpleName)
        if (fragment !is PetCareFragment) {
            Log.d("MyFlexibleFragment", "Fragment Name :" + PetCareFragment::class.java.simpleName)
            fragmentManager
                .beginTransaction()
                .add(R.id.frame_container_petcare, petCareFragment, PetCareFragment::class.java.simpleName)
                .commit()
        }
    }
}