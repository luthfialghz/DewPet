package com.bangkit.dewpet.data.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RequestDiagnose {
    @SerializedName("penyakit")
    @Expose
    var penyakit : ArrayList<String>? = null

    @SerializedName("hewan")
    @Expose
    var hewan: String? = null
}