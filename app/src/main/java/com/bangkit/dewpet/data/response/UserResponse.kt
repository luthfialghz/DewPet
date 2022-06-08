package com.bangkit.dewpet.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserResponse(
	@SerializedName("email")
	@Expose
	val email: String? = null,

	@SerializedName("password")
	@Expose
	val password: String? = null,

	@SerializedName("role")
	@Expose
	val role: String? = null,

	@SerializedName("namaL")
	@Expose
	val namaL: String? = null,

	@SerializedName("token")
	@Expose
	val token: String? = null
)
