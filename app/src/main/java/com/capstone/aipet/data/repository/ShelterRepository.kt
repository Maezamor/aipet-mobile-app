package com.capstone.aipet.data.repository

import com.capstone.aipet.data.remote.DataResult
import com.capstone.aipet.data.remote.api.ApiService
import com.capstone.aipet.data.remote.response.history.ItemHistory
import com.capstone.aipet.data.remote.response.maps.ItemShelter
import com.capstone.aipet.data.remote.response.maps.ResponseShelter

class ShelterRepository(private val apiService: ApiService) {

    suspend fun getShelter(): DataResult<List<ItemShelter>> {
        return try {
            val response = apiService.getListShelter()
            DataResult.Success(response.itemShelter)
        } catch (e: Exception) {
            DataResult.Error(e.message ?: "${e.message}")
        }
    }
}