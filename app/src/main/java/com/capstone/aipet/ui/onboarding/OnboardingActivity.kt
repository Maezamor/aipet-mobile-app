package com.capstone.aipet.ui.onboarding


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.capstone.aipet.R
import com.capstone.aipet.databinding.ActivityOnboardingBinding
import com.capstone.aipet.ui.onboarding.personality.filter.FilterOnBoardingFragment

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager = supportFragmentManager
        val filterOnBoardingFragment = FilterOnBoardingFragment()
        val fragment = fragmentManager.findFragmentByTag(FilterOnBoardingFragment::class.java.simpleName)
        if (fragment !is FilterOnBoardingFragment) {
            Log.d("MyFlexibleFragment", "Fragment Name :" + filterOnBoardingFragment::class.java.simpleName)
            fragmentManager
                .beginTransaction()
                .add(R.id.frame_container, filterOnBoardingFragment, FilterOnBoardingFragment::class.java.simpleName)
                .commit()
        }
    }

}