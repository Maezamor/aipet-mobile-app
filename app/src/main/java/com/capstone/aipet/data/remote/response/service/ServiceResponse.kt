package com.capstone.aipet.data.remote.response.service

import com.google.gson.annotations.SerializedName

data class ServiceResponse(

	@field:SerializedName("data")
	val servicesResponse: ServiceDataResponse
)

