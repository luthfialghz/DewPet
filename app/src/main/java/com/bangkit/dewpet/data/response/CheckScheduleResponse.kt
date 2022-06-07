package com.bangkit.dewpet.data.response

import com.google.gson.annotations.SerializedName

data class CheckScheduleResponse(

	@field:SerializedName("CheckScheduleResponse")
	val checkScheduleResponse: List<CheckScheduleResponseItem?>? = null
)

data class CheckScheduleResponseItem(

	@field:SerializedName("service_id")
	val serviceId: Int? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("day")
	val day: String? = null,

	@field:SerializedName("close")
	val close: String? = null,

	@field:SerializedName("namaL")
	val namaL: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("open")
	val open: String? = null
)
