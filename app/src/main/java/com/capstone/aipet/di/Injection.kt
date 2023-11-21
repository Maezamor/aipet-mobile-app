package com.capstone.aipet.di

import android.content.Context
import com.capstone.aipet.data.remote.api.ApiConfig
import com.capstone.aipet.data.repository.UserRepository

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService(context)
        return UserRepository(apiService)
    }
}