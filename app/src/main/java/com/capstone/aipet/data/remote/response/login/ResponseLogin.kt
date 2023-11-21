package com.capstone.aipet.data.remote.response.login

import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @SerializedName("errors")
    val errors: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val loginResult: ResultLogin,
)
