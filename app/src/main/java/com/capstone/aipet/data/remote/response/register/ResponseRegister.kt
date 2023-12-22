package com.capstone.aipet.data.remote.response.register

import com.google.gson.annotations.SerializedName

data class ResponseRegister(
    @field:SerializedName("errors")
    val itemErrors : Boolean,
    @field:SerializedName("success")
    val success: String,
)
//data class ItemErrors(
//    @SerializedName("email")
//    val email: String,
//)
