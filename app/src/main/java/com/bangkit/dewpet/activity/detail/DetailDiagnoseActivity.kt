package com.bangkit.dewpet.activity.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.dewpet.R
import com.bangkit.dewpet.activity.MainActivity
import com.bangkit.dewpet.databinding.ActivityDetailDiagnoseBinding
import com.bangkit.dewpet.features.DewCareActivity
import com.bangkit.dewpet.features.DewTecActivity
import com.bangkit.dewpet.features.DewVetActivity

class DetailDiagnoseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailDiagnoseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDiagnoseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.tvDisease.text = intent.getStringExtra("EXTRA_DISEASE")
        binding.tvDiseaseCategory.text = intent.getStringExtra("EXTRA_CATEGORY")
        binding.tvDiseaseEng.text = intent.getStringExtra("EXTRA_DISEASE_EN")
        binding.tvDiseaseEtc.text = intent.getStringExtra("EXTRA_ETC")

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, DewTecActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnFindPetcare.setOnClickListener {
            val intent = Intent(this, DewCareActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnCallDoctor.setOnClickListener {
            val intent = Intent(this, DewVetActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}