package com.capstone.aipet.ui.onboarding.personality.form

import androidx.lifecycle.ViewModel
import com.capstone.aipet.data.repository.DogsRepository

class FormPersonalityViewModel(private val repository: DogsRepository) : ViewModel() {
    fun postRequestStory(requestStory: String) = repository.postRequestStory(requestStory)
}