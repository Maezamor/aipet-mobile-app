package com.capstone.aipet.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.capstone.aipet.data.remote.DataResult
import com.capstone.aipet.data.remote.api.ApiService
import com.capstone.aipet.data.remote.response.dogs.DetailDogResponse
import com.capstone.aipet.data.remote.response.login.ResponseLogin
import com.capstone.aipet.data.remote.response.register.ResponseRegister

class UserRepository(
    private val apiService: ApiService
) {
    fun registerRepo(username: String,name: String,address: String,phone: String, email: String, password: String): LiveData<DataResult<ResponseRegister>> = liveData {
        emit(DataResult.Loading)
        try {
            val response = apiService.register(username, name, address, phone, email, password)
            emit(DataResult.Success(response))
        } catch (e: Exception) {
            Log.e("RegisterViewModel", "postSignUp: ${e.message.toString()}")
            emit(DataResult.Error(e.message.toString()))
        }
    }

    fun loginRepo(email: String, password: String): LiveData<DataResult<ResponseLogin>> = liveData {
        emit(DataResult.Loading)
        try {
            val response = apiService.login(email, password)
            emit(DataResult.Success(response))
        } catch (e: Exception) {
            Log.e("LoginViewModel", "postLogin: ${e.message.toString()}")
            emit(DataResult.Error(e.message.toString()))
        }
    }
}