package com.bangkit.dewpet.features

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.dewpet.R
import com.bangkit.dewpet.activity.MainActivity
import com.bangkit.dewpet.databinding.ActivityDewStoreBinding

class DewStoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDewStoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDewStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.tvBackStore.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}