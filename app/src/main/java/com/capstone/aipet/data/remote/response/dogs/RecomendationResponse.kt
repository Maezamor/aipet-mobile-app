package com.capstone.aipet.data.remote.response.dogs

import com.google.gson.annotations.SerializedName

data class RecomendationResponse(

	@field:SerializedName("data")
	val data: List<ItemRecomend?>? = null
)

data class ItemRecomend(

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
