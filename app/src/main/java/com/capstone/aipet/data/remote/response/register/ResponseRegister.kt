package com.capstone.aipet.data.remote.response.register

import com.google.gson.annotations.SerializedName

data class ResponseRegister(
    @field:SerializedName("errors")
    val errors: Boolean,
    @field:SerializedName("success")
    val success: String,
)
