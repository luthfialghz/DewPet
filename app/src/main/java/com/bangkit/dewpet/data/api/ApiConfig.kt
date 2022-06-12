package com.bangkit.dewpet.data.api

import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

class ApiConfig {
    fun getRetrofitClientInstance(): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl("https://dewpetproject.et.r.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}