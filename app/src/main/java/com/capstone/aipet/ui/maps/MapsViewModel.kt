package com.capstone.aipet.ui.maps

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.aipet.data.remote.DataResult
import com.capstone.aipet.data.remote.response.history.HistoryResponse
import com.capstone.aipet.data.remote.response.maps.ItemShelter
import com.capstone.aipet.data.repository.ShelterRepository
import kotlinx.coroutines.launch

class MapsViewModel(private val repository: ShelterRepository): ViewModel() {
    private val _shelter = MutableLiveData<HistoryResponse>()
    val shelter: LiveData<HistoryResponse>
        get() = _shelter
    private val _shelterList = MutableLiveData<DataResult<List<ItemShelter>>>()
    val shelterList: LiveData<DataResult<List<ItemShelter>>> get() = _shelterList
    fun getShelterList() {
        viewModelScope.launch {
            _shelterList.value = DataResult.Loading
            _shelterList.value = repository.getShelter()
        }
    }
//    fun getHistoryById() {
//        viewModelScope.launch {
//            try {
//                val response = repository.getShelter()
//                _shelter.value = response
//            } catch (e: Exception) {
//                // Handle error, misalnya tampilkan pesan kesalahan
//            }
//        }
//    }
}