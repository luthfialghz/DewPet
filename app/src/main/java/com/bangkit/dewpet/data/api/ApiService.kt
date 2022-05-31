package com.bangkit.dewpet.data.api

import com.bangkit.dewpet.data.request.RequestLogin
import com.bangkit.dewpet.data.request.RequestRegister
import com.bangkit.dewpet.data.response.LoginResponse
import com.bangkit.dewpet.data.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("regis")
    fun register(
        @Body userRequest: RequestRegister
    ) : Call<RegisterResponse>

    @POST("login")
    fun login(
        @Body userRequest: RequestLogin
    ) : Call<LoginResponse>
}