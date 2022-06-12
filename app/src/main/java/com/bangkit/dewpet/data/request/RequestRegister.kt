package com.bangkit.dewpet.data.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RequestRegister {
    @SerializedName("namaL")
    @Expose
    var name: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("password")
    @Expose
    var password: String? = null
}