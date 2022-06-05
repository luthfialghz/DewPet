package com.bangkit.dewpet.data.preferences

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    var token: String? = null,
    var name: String? = null
) : Parcelable
