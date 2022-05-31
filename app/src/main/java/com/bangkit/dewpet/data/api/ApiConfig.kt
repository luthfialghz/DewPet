package com.bangkit.dewpet.data.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    fun getRetrofitClientInstance(): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl("https://dewpetproject.et.r.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}