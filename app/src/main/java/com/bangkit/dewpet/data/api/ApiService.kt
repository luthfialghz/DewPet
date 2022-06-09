package com.bangkit.dewpet.data.api

import com.bangkit.dewpet.data.request.*
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
    fun vetSchedule() : Call<VetResponse>

    @POST("login/checklogin")
    fun getUser(
        @Body userRequest: RequestUser
    ) : Call<UserResponse>

    @POST("appointment")
    fun reqAppointment(
        @Body userRequest: RequestAppointment
    ) : Call<VetAppointmentResponse>

    @POST("jadwalbook")
    fun statusAppointment(
        @Body userRequest: RequestAppointmentStatus
    ) : Call<VetAppointmentStatusResponse>

    @DELETE("hapus/{id}")
    fun deleteAppointment(
        @Path("id") id: Int
    ): Call<DeleteVetAppointmentStatusResponse>

    @POST("edited")
    fun editAppointment(
        @Body userRequest: RequestEditAppointment
    ): Call<EditVetAppointmentResponse>

    @GET("listgejala")
    fun listIndication(): Call<IndicationResponse>
}