package com.capstone.aipet.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.capstone.aipet.data.remote.DataResult
import com.capstone.aipet.data.remote.response.dogs.ItemDogs
import com.capstone.aipet.data.remote.response.dogs.ItemRecomend
import com.capstone.aipet.data.remote.response.dogs.RecomendationResponse
import com.capstone.aipet.data.repository.DogsRepository
import kotlinx.coroutines.launch

class HomeViewModel(private  val repository: DogsRepository) : ViewModel() {
    val dogs: LiveData<PagingData<ItemDogs>> = repository.getDogs().cachedIn(viewModelScope)
    val matchDogs: LiveData<PagingData<ItemDogs>> = repository.getMatchDogs().cachedIn(viewModelScope)

    private val _recomend = MutableLiveData<RecomendationResponse>()
    val recomend: LiveData<RecomendationResponse>
        get() = _recomend
    private val _recomendList = MutableLiveData<DataResult<List<ItemRecomend?>?>>()
    val historyList: LiveData<DataResult<List<ItemRecomend?>?>> get() = _recomendList
    fun getRecomendList() {
        viewModelScope.launch {
            _recomendList.value = DataResult.Loading
            _recomendList.value = repository.getRecomendation()
        }
    }
}