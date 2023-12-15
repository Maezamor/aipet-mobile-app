package com.capstone.aipet.data.remote.response.rescue

import com.google.gson.annotations.SerializedName

data class ResponseRescue(

	@field:SerializedName("data")
	val listRescue: ItemRescue,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class ItemRescue(
	@field:SerializedName("rescue_story")
	val rescueStory: String,
	@field:SerializedName("id")
	val id: Int? = null
)
