package com.capstone.aipet.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.capstone.aipet.data.filterData.sterilization.Sterilization
import com.capstone.aipet.data.remote.DataResult
import com.capstone.aipet.data.remote.api.ApiService
import com.capstone.aipet.data.remote.paging.DogsPaging
import com.capstone.aipet.data.remote.response.checkout.CheckoutResponse
import com.capstone.aipet.data.remote.response.dogs.DetailDogResponse
import com.capstone.aipet.data.remote.response.dogs.ItemDogs
import com.capstone.aipet.data.remote.response.history.HistoryResponse
import com.capstone.aipet.data.remote.response.history.ItemHistory
import com.capstone.aipet.data.remote.response.login.ResponseLogin
import com.capstone.aipet.data.remote.response.onboarding.ResponseOnboarding
import com.capstone.aipet.data.remote.response.rescue.ItemRescue
import com.capstone.aipet.data.remote.response.rescue.ResponseRescue
import retrofit2.Response

class DogsRepository(
    private val apiService: ApiService) {
    fun getDogs(): LiveData<PagingData<ItemDogs>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                DogsPaging(apiService)
            }
        ).liveData
    }
    fun getMatchDogs(): LiveData<PagingData<ItemDogs>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1
            ),
            pagingSourceFactory = {
                DogsPaging(apiService)
            }
        ).liveData
    }


    suspend fun getDetailDogs(id: Int): DetailDogResponse {
        return apiService.getDetailDogs(id)
    }
    suspend fun getHistory(id: Int): HistoryResponse {
        return apiService.historyAdoption(id)
    }
    suspend fun getHistoryList(userId: Int): DataResult<List<ItemHistory>> {
        return try {
            val response = apiService.historyAdoption(userId)
            DataResult.Success(response.data?.data ?: emptyList())
        } catch (e: Exception) {
            DataResult.Error(e.message ?: "Terjadi kesalahan saat mengambil data riwayat.")
        }
    }
    suspend fun checkoutDogs(dogId: Int): DataResult<CheckoutResponse> {
        return try {
            val response = apiService.checkoutDogs(dogId)
            DataResult.Success(response)
        } catch (e: Exception) {
            Log.e("DogsRepository", "checkoutDogs: ${e.message.toString()}")
            DataResult.Error(e.message.toString())
        }
    }
    fun postOnboarding1(group: String,sterilization: String,age: String): LiveData<DataResult<ResponseOnboarding>> = liveData {
        emit(DataResult.Loading)
        try {
            val response = apiService.postOnboarding1(group, sterilization,age)
            emit(DataResult.Success(response))
        } catch (e: Exception) {
            Log.e("FilterOnboardingViewModel", "postOnBoarding1: ${e.message.toString()}")
            emit(DataResult.Error(e.message.toString()))
        }
    }
    fun postOnboarding2(rescueStory1: String,rescueStory2: String,rescueStory3: String): LiveData<DataResult<ResponseOnboarding>> = liveData {
        emit(DataResult.Loading)
        try {
            val response = apiService.postOnboarding2(rescueStory1,rescueStory2,rescueStory3)
            emit(DataResult.Success(response))
        } catch (e: Exception) {
            Log.e("OnBoarding2ViewModel", "postOnBoarding2: ${e.message.toString()}")
            emit(DataResult.Error(e.message.toString()))
        }
    }
    fun postRequestStory(requestStory: String): LiveData<DataResult<ResponseOnboarding>> = liveData {
        emit(DataResult.Loading)
        try {
            val response = apiService.postRequestStory(requestStory)
            emit(DataResult.Success(response))
        } catch (e: Exception) {
            Log.e("OnBoarding2ViewModel", "postRequestStory: ${e.message.toString()}")
            emit(DataResult.Error(e.message.toString()))
        }
    }

    suspend fun getRescue(): ResponseRescue {
        return apiService.getRescue()
    }





}