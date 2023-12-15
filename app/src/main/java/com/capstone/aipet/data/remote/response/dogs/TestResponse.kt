package com.capstone.aipet.data.remote.response.dogs

import com.google.gson.annotations.SerializedName

data class TestResponse(

	@field:SerializedName("data")
	val responseDogs: ResponseDogs,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

//data class DataItem(
//
//	@field:SerializedName("gender")
//	val gender: String? = null,
//
//	@field:SerializedName("type_id")
//	val typeId: Int? = null,
//
//	@field:SerializedName("reads")
//	val reads: Int? = null,
//
//	@field:SerializedName("created_at")
//	val createdAt: String? = null,
//
//	@field:SerializedName("deleted_at")
//	val deletedAt: Any? = null,
//
//	@field:SerializedName("picture")
//	val picture: String? = null,
//
//	@field:SerializedName("character")
//	val character: String? = null,
//
//	@field:SerializedName("updated_at")
//	val updatedAt: String? = null,
//
//	@field:SerializedName("steril_id")
//	val sterilId: Int? = null,
//
//	@field:SerializedName("name")
//	val name: String? = null,
//
//	@field:SerializedName("rescue_story")
//	val rescueStory: String? = null,
//
//	@field:SerializedName("id")
//	val id: Int? = null,
//
//	@field:SerializedName("age")
//	val age: String? = null,
//
//	@field:SerializedName("selter_id")
//	val selterId: Int? = null
//)
//
//data class Data(
//
//	@field:SerializedName("per_page")
//	val perPage: Int? = null,
//
//	@field:SerializedName("data")
//	val data: List<DataItem?>? = null,
//
//	@field:SerializedName("last_page")
//	val lastPage: Int? = null,
//
//	@field:SerializedName("next_page_url")
//	val nextPageUrl: String? = null,
//
//	@field:SerializedName("prev_page_url")
//	val prevPageUrl: Any? = null,
//
//	@field:SerializedName("first_page_url")
//	val firstPageUrl: String? = null,
//
//	@field:SerializedName("path")
//	val path: String? = null,
//
//	@field:SerializedName("total")
//	val total: Int? = null,
//
//	@field:SerializedName("last_page_url")
//	val lastPageUrl: String? = null,
//
//	@field:SerializedName("from")
//	val from: Int? = null,
//
//	@field:SerializedName("links")
//	val links: List<LinksItem?>? = null,
//
//	@field:SerializedName("to")
//	val to: Int? = null,
//
//	@field:SerializedName("current_page")
//	val currentPage: Int? = null
//)
//
//data class LinksItem(
//
//	@field:SerializedName("active")
//	val active: Boolean? = null,
//
//	@field:SerializedName("label")
//	val label: String? = null,
//
//	@field:SerializedName("url")
//	val url: Any? = null
//)
