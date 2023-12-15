package com.capstone.aipet.data.remote.response.service

import com.google.gson.annotations.SerializedName

data class ItemServices (
    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("city")
    val city: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("lon")
    val lon: Any? = null,

    @field:SerializedName("deleted_at")
    val deletedAt: Any? = null,

    @field:SerializedName("picture")
    val picture: String? = null,

    @field:SerializedName("sosial_media_2")
    val sosialMedia2: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("sosial_media_1")
    val sosialMedia1: String? = null,

    @field:SerializedName("phone")
    val phone: String? = null,

    @field:SerializedName("sosial_media_3")
    val sosialMedia3: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("let")
    val let: Any,

    @field:SerializedName("id")
    val id: Int
)