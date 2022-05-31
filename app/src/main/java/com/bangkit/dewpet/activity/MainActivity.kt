package com.bangkit.dewpet.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.dewpet.features.DewConsulActivity
import com.bangkit.dewpet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.ivDewConsul.setOnClickListener{
            val intent = Intent(this, DewConsulActivity::class.java)
            startActivity(intent)
        }
    }
}