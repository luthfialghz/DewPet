package com.bangkit.dewpet.data.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RequestUser {
    @SerializedName("token")
    @Expose
    var token: String? = null
}