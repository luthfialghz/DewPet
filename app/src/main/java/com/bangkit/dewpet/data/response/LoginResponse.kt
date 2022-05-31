package com.bangkit.dewpet.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("role")
    @Expose
    val role: String? = null,

    @SerializedName("namaL")
    @Expose
    val namaL: String? = null
)
