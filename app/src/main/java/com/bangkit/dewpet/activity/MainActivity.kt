package com.bangkit.dewpet.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bangkit.dewpet.data.preferences.UserPref
import com.bangkit.dewpet.features.DewConsulActivity
import com.bangkit.dewpet.databinding.ActivityMainBinding
import com.bangkit.dewpet.features.DewCareActivity
import com.bangkit.dewpet.features.SettingsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userPref: UserPref

    val PREF_NAME = "AUTH_PREF"
    val KEY_NAME = "key.name"

    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        supportActionBar?.hide()
        userPref = UserPref(this)

        val profileName : String? = sharedPref.getString(KEY_NAME, null)
        Log.e("Homepage", profileName.toString())
        binding.tvNameUser.text = profileName.toString()


        binding.ivDewConsul.setOnClickListener{
            val intent = Intent(this, DewConsulActivity::class.java)
            startActivity(intent)
        }

        binding.ivSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        binding.ivDewCare.setOnClickListener {
            val intent = Intent(this, DewCareActivity::class.java)
            startActivity(intent)
        }
    }
}