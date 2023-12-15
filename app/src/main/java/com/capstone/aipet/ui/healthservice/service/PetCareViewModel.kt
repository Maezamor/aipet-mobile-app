package com.capstone.aipet.ui.healthservice.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.capstone.aipet.data.remote.response.service.ItemServices
import com.capstone.aipet.data.repository.ServiceRepository

class PetCareViewModel(repository: ServiceRepository) : ViewModel() {
    val services: LiveData<PagingData<ItemServices>> = repository.getServices().cachedIn(viewModelScope)
}