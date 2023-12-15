package com.capstone.aipet.ui.home.detaildog

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.aipet.data.remote.DataResult
import com.capstone.aipet.data.remote.response.checkout.CheckoutResponse
import com.capstone.aipet.data.remote.response.dogs.DetailDogResponse
import com.capstone.aipet.data.repository.DogsRepository
import kotlinx.coroutines.launch

class DetailDogViewModel(private val repository: DogsRepository) : ViewModel() {
    private val _detailDog = MutableLiveData<DetailDogResponse>()
    private val _checkoutResult = MutableLiveData<DataResult<CheckoutResponse>>()
    val checkoutResult: LiveData<DataResult<CheckoutResponse>> get() = _checkoutResult
    val detailDog: LiveData<DetailDogResponse>
        get() = _detailDog

    fun getDetailDogById(id: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getDetailDogs(id)
                _detailDog.value = response
            } catch (e: Exception) {
                // Handle error, misalnya tampilkan pesan kesalahan
            }
        }
    }
    fun checkoutDog(dogId: Int) {
        viewModelScope.launch {
            _checkoutResult.value = DataResult.Loading
            try {
                val response = repository.checkoutDogs(dogId)
                _checkoutResult.value = response
            } catch (e: Exception) {
                Log.e("DetailDogViewModel", "checkoutDog: ${e.message.toString()}")
                _checkoutResult.value = DataResult.Error(e.message.toString())
            }
        }
    }
}