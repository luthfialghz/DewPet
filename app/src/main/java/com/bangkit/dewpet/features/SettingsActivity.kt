package com.bangkit.dewpet.features

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bangkit.dewpet.R
import com.bangkit.dewpet.activity.MainActivity
import com.bangkit.dewpet.activity.OnboardingActivity
import com.bangkit.dewpet.data.api.ApiConfig
import com.bangkit.dewpet.data.api.ApiService
import com.bangkit.dewpet.data.preferences.UserPref
import com.bangkit.dewpet.data.request.RequestUser
import com.bangkit.dewpet.data.response.UserResponse
import com.bangkit.dewpet.databinding.ActivitySettingsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingsActivity : AppCompatActivity() {

    private lateinit var userPref: UserPref
    private lateinit var binding: ActivitySettingsBinding

    val PREF_NAME = "AUTH_PREF"
    val KEY_TOKEN = "key.token"

    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        supportActionBar?.hide()
        userPref = UserPref(this)

        binding.btnBack.setOnClickListener {
            startActivity(
                Intent(this, MainActivity::class.java)
            )
            finish()
        }

        getUserData()
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
        finishAffinity()
    }

    private fun getUserData(){
        startProg()
        val request = RequestUser()
        var token : String? = sharedPref.getString(KEY_TOKEN, null)
        request.token = token
        val retrofit = ApiConfig().getRetrofitClientInstance().create(ApiService::class.java)
        retrofit.getUser(request).enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val data = response.body()
                binding.tvUserName.text = data?.namaL
                binding.tvUserEmail.text = data?.email
                stopProg()
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }
        })
    }

    private fun startProg() {
        binding.pBar.visibility = View.VISIBLE
    }

    private fun stopProg() {
        binding.pBar.visibility = View.GONE
    }
}