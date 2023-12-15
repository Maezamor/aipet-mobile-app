package com.capstone.aipet.ui.Guide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.capstone.aipet.R
import com.capstone.aipet.databinding.ActivityGuideBinding
import com.capstone.aipet.ui.Guide.homeguide.HomeGuideFragment

class GuideActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGuideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuideBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragmentManager = supportFragmentManager
        val homeGuideFragment = HomeGuideFragment()
        val fragment = fragmentManager.findFragmentByTag(HomeGuideFragment::class.java.simpleName)
        if (fragment !is HomeGuideFragment) {
            Log.d("MyFlexibleFragment", "Fragment Name :" + HomeGuideFragment::class.java.simpleName)
            fragmentManager
                .beginTransaction()
                .add(R.id.frame_container, homeGuideFragment, HomeGuideFragment::class.java.simpleName)
                .commit()
        }
    }
}