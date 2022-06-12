package com.bangkit.dewpet.data.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RequestAppointment{
    @SerializedName("client_email")
    @Expose
    var client_email: String? = null

    @SerializedName("start_at")
    @Expose
    var start_at: String? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("service_id")
    @Expose
    var service_id: Int? = null
}
