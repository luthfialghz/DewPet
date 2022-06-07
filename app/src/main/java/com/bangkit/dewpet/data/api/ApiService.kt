package com.bangkit.dewpet.data.api

import com.bangkit.dewpet.data.request.RequestLogin
import com.bangkit.dewpet.data.request.RequestRegister
import com.bangkit.dewpet.data.response.*
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface ApiService {
    @POST("regis")
    fun register(
        @Body userRequest: RequestRegister
    ) : Call<RegisterResponse>

    @POST("login")
    fun login(
        @Body userRequest: RequestLogin
    ) : Call<LoginResponse>

    @GET("articles")
    fun articles() : Call<ArticleResponse>

    @GET("jadwalcek")
    fun checkAppoinment() : Call<CheckScheduleResponse>
//
//    @GET("vet")
//    fun doctor(): Call<DoctorListResponse>
}