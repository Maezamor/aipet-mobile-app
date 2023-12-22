package com.capstone.aipet.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.aipet.data.remote.DataResult
import com.capstone.aipet.data.remote.response.history.HistoryResponse
import com.capstone.aipet.data.remote.response.history.ItemHistory
import com.capstone.aipet.data.repository.DogsRepository
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: DogsRepository): ViewModel() {

    private val _history = MutableLiveData<HistoryResponse>()
    val history: LiveData<HistoryResponse>
        get() = _history
    private val _historyList = MutableLiveData<DataResult<List<ItemHistory>>>()
    val historyList: LiveData<DataResult<List<ItemHistory>>> get() = _historyList
    fun getHistoryList(userId: Int) {
        viewModelScope.launch {
            _historyList.value = DataResult.Loading
            _historyList.value = repository.getHistoryList(userId)
        }
    }

}