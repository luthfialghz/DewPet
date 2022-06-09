package com.bangkit.dewpet.data.response

import com.google.gson.annotations.SerializedName

data class IndicatorResponse(

	@field:SerializedName("penyakit")
	val penyakit: List<PenyakitItem>,

	@field:SerializedName("gejala")
	val gejala: List<GejalaItem>
){
	data class GejalaItem(

		@field:SerializedName("nama_gejala")
		val namaGejala: String? = null,

		@field:SerializedName("id")
		val id: Int? = null
	)

	data class PenyakitItem(

		@field:SerializedName("nama_penyakit")
		val namaPenyakit: String? = null,

		@field:SerializedName("id")
		val id: Int? = null
	)
}
