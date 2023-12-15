package com.capstone.aipet.data.remote.response.dogs

import com.google.gson.annotations.SerializedName

data class DetailDogResponse(

	@field:SerializedName("data")
	val data: Data? = null
)

data class Dog(

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("type_id")
	val typeId: Int? = null,

	@field:SerializedName("reads")
	val reads: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("deleted_at")
	val deletedAt: Any? = null,

	@field:SerializedName("picture")
	val picture: String? = null,

	@field:SerializedName("character")
	val character: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("steril_id")
	val sterilId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("rescue_story")
	val rescueStory: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("age")
	val age: String? = null,

	@field:SerializedName("selter_id")
	val selterId: Int? = null
)

data class Selter(

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("lon")
	val lon: Double? = null,

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
	val let: Double? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Type(

	@field:SerializedName("activity_level")
	val activityLevel: String? = null,

	@field:SerializedName("size")
	val size: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: Any? = null,

	@field:SerializedName("groups")
	val groups: String? = null,

	@field:SerializedName("created_at")
	val createdAt: Any? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("deleted_at")
	val deletedAt: Any? = null
)

data class Gender(

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("deleted_at")
	val deletedAt: Any? = null
)

data class Data(

	@field:SerializedName("Type")
	val type: Type? = null,

	@field:SerializedName("gender")
	val gender: Gender? = null,

	@field:SerializedName("selter")
	val selter: Selter? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("dog")
	val dog: Dog? = null
)
