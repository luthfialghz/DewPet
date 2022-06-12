package com.bangkit.dewpet.data.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RequestUpdateStatus {
    @SerializedName("approved")
    @Expose
    var approved: String? = null
}