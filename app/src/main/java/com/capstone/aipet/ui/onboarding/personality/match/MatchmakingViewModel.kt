package com.capstone.aipet.ui.onboarding.personality.match

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.aipet.data.remote.response.rescue.ResponseRescue
import com.capstone.aipet.data.repository.DogsRepository
import kotlinx.coroutines.launch

class MatchmakingViewModel(private val repository: DogsRepository) : ViewModel() {
    private val _rescueDog = MutableLiveData<ResponseRescue>()
    val rescueDog: LiveData<ResponseRescue>
        get() = _rescueDog
    fun getRescue() {
        viewModelScope.launch {
            try {
                val response = repository.getRescue()
                _rescueDog.value = response
            } catch (e: Exception) {
                // Handle error, misalnya tampilkan pesan kesalahan
                Log.d("matchmakingViewModel", "error : ${e.message.toString()}")
            }
        }
    }
    fun onBoarding2(rescueStory1: String,rescueStory2: String,rescueStory3: String) = repository.postOnboarding2(rescueStory1,rescueStory2,rescueStory3)


}