package com.bangkit.dewpet.data.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RequestEditAppointment {
    @SerializedName("start_at")
    @Expose
    var start_at: String? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("service_id")
    @Expose
    var service_id: Int? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null
}