package com.capstone.aipet.ui.onboarding.personality.filter

import androidx.lifecycle.ViewModel
import com.capstone.aipet.data.repository.DogsRepository

class FilterOnBoardingViewModel(private val repository: DogsRepository): ViewModel() {
    fun onBoarding1(group: String,sterilization: String,age: String) = repository.postOnboarding1(group, sterilization,age)
}