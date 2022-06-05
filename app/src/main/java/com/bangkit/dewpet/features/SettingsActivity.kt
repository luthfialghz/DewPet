package com.bangkit.dewpet.features

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.dewpet.R
import com.bangkit.dewpet.activity.OnboardingActivity
import com.bangkit.dewpet.data.preferences.UserPref
import com.bangkit.dewpet.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var userPref: UserPref
    private lateinit var binding: ActivitySettingsBinding

    val PREF_NAME = "AUTH_PREF"
    val KEY_TOKEN = "key.email"
    val KEY_NAME = "key.password"

    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        supportActionBar?.hide()
        userPref = UserPref(this)

        binding.btnLogout.setOnClickListener{
            clearData()
        }
    }

    private fun clearData() {
        val editor = sharedPref.edit()
        editor.clear()
        editor.apply()
        val intent = Intent(this, OnboardingActivity::class.java)
        startActivity(intent)
        finish()
    }
}