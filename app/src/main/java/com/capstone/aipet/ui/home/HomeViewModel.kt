package com.capstone.aipet.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.capstone.aipet.data.remote.response.dogs.ItemDogs
import com.capstone.aipet.data.repository.DogsRepository

class HomeViewModel(private  val repository: DogsRepository) : ViewModel() {
    val dogs: LiveData<PagingData<ItemDogs>> = repository.getDogs().cachedIn(viewModelScope)
    val matchDogs: LiveData<PagingData<ItemDogs>> = repository.getMatchDogs().cachedIn(viewModelScope)
}