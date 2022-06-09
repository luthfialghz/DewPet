package com.bangkit.dewpet.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.dewpet.R
import com.bangkit.dewpet.data.api.ApiConfig
import com.bangkit.dewpet.data.api.ApiService
import com.bangkit.dewpet.databinding.ActivityDewTecBinding

class DewTecActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDewTecBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDewTecBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun getDisease(){
        val retrofit = ApiConfig().getRetrofitClientInstance().create(ApiService::class.java)
    }
}