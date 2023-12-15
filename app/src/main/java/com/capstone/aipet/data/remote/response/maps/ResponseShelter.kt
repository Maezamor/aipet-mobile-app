package com.capstone.aipet.data.remote.response.maps

import com.google.gson.annotations.SerializedName

data class ResponseShelter(

	@field:SerializedName("data")
	val itemShelter: List<ItemShelter> ,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class ItemShelter(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("lon")
	val lon: Double? = null,

	@field:SerializedName("let")
	val let: Double? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
