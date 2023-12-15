package com.capstone.aipet.data.remote.api

import android.content.Context
import com.capstone.aipet.pref.UserPreference
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        fun getApiService(context: Context): ApiService {
            val sharedPref = UserPreference.init(context, "onSignIn")
            val token = sharedPref.getString("token", null).toString()
            val retrofit = Retrofit.Builder()
                .baseUrl("http://34.124.203.199:8001/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getInterceptor(token))
                .build()
            return retrofit.create(ApiService::class.java)
        }
        private fun getInterceptor(token: String?): OkHttpClient {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            return if (token.isNullOrEmpty()) {
                OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build()
            } else {
                OkHttpClient.Builder()
                    .addInterceptor(ApiInterceptor(token))
                    .addInterceptor(loggingInterceptor)
                    .build()
            }
        }
    }
}