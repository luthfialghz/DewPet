package com.bangkit.dewpet.data.response

import com.google.gson.annotations.SerializedName

data class DiagnoseResponse(

	@field:SerializedName("hasil")
	val hasil: Hasil? = null,

	@field:SerializedName("kategori")
	val kategori: String? = null
) {
	data class Hasil(

		@field:SerializedName("en")
		val en: String? = null,

		@field:SerializedName("id")
		val id: String? = null
	)
}
