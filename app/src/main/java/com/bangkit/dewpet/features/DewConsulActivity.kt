package com.bangkit.dewpet.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.dewpet.R
import com.bangkit.dewpet.databinding.ActivityDewConsulBinding

class DewConsulActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDewConsulBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDewConsulBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
    }
}