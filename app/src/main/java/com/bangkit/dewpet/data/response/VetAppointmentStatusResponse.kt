package com.bangkit.dewpet.data.response

import com.google.gson.annotations.SerializedName

data class VetAppointmentStatusResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("status")
	val status: String? = null
) {
	data class DataItem(

		@field:SerializedName("approved")
		val approved: String? = null,

		@field:SerializedName("service_id")
		val serviceId: Int? = null,

		@field:SerializedName("client_email")
		val clientEmail: String? = null,

		@field:SerializedName("location")
		val location: String? = null,

		@field:SerializedName("id")
		val id: Int? = null,

		@field:SerializedName("modified_at")
		val modifiedAt: String? = null,

		@field:SerializedName("start_at")
		val startAt: String? = null,

		@field:SerializedName("message")
		val message: String? = null,

		@field:SerializedName("namaL")
		val namaL: String? = null
	)
}
