package com.capstone.aipet.data.remote.response.login

import com.google.gson.annotations.SerializedName

data class ResultLogin(
    @SerializedName("id")
    val userId: Int,
    @SerializedName("username")
    val username: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("token")
    val token: String,
)