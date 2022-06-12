package com.bangkit.dewpet.data.response

import com.google.gson.annotations.SerializedName

data class EditVetAppointmentResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("status")
	val status: String? = null
) {
	data class Data(

		@field:SerializedName("fieldCount")
		val fieldCount: Int? = null,

		@field:SerializedName("serverStatus")
		val serverStatus: Int? = null,

		@field:SerializedName("protocol41")
		val protocol41: Boolean? = null,

		@field:SerializedName("changedRows")
		val changedRows: Int? = null,

		@field:SerializedName("affectedRows")
		val affectedRows: Int? = null,

		@field:SerializedName("warningCount")
		val warningCount: Int? = null,

		@field:SerializedName("message")
		val message: String? = null,

		@field:SerializedName("insertId")
		val insertId: Int? = null
	)

}
