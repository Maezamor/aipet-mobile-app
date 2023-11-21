package com.capstone.aipet.data.remote.api

import com.capstone.aipet.data.remote.response.login.ResponseLogin
import com.capstone.aipet.data.remote.response.register.ResponseRegister
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("users")
    suspend fun register(
        @Field("username") username: String,
        @Field("name") name: String,
        @Field("address") address: String,
        @Field("phone") phone: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): ResponseRegister

    @FormUrlEncoded
    @POST("users/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): ResponseLogin
}