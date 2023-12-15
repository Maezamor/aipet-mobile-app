package com.capstone.aipet.data.remote.response.checkout

import com.google.gson.annotations.SerializedName

data class CheckoutResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("time")
	val time: String? = null
)

data class Data(

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("dog_id")
	val dogId: Int? = null
)
