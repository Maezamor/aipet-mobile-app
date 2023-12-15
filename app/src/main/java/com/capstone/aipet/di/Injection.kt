package com.capstone.aipet.di

import android.content.Context
import com.capstone.aipet.data.remote.api.ApiConfig
import com.capstone.aipet.data.repository.DogsRepository
import com.capstone.aipet.data.repository.ServiceRepository
import com.capstone.aipet.data.repository.ShelterRepository
import com.capstone.aipet.data.repository.UserRepository

object Injection {
    fun provideUserRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService(context)
        return UserRepository(apiService)
    }
    fun provideDogsRepository(context: Context): DogsRepository {
        val apiService = ApiConfig.getApiService(context)
        return DogsRepository(apiService)
    }
    fun provideServicesRepository(context: Context): ServiceRepository {
        val apiService = ApiConfig.getApiService(context)
        return ServiceRepository(apiService)
    }
    fun provideSheltersRepository(context: Context): ShelterRepository {
        val apiService = ApiConfig.getApiService(context)
        return ShelterRepository(apiService)
    }
}