package com.bangkit.dewpet.data.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RequestAppointmentStatus {
    @SerializedName("client_email")
    @Expose
    var client_email: String? = null
}