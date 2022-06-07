package com.bangkit.dewpet.data.response

import com.google.gson.annotations.SerializedName

object Model {
	data class DoctorListResponseItem(
		val vetId: Int,
		val namaL: String,
		val email: String
	)}
