package com.bangkit.dewpet.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("status")
    @Expose
    val status: String? = null
)
