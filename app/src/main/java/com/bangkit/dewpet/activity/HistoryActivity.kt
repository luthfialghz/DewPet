package com.bangkit.dewpet.activity

import android.content.Context
import android.content.SharedPreferences
import android.icu.util.LocaleData
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.bangkit.dewpet.R
import com.bangkit.dewpet.data.api.ApiConfig
import com.bangkit.dewpet.data.api.ApiService
import com.bangkit.dewpet.data.preferences.UserPref
import com.bangkit.dewpet.data.request.RequestAppointmentStatus
import com.bangkit.dewpet.data.request.RequestUser
import com.bangkit.dewpet.data.response.ArticleResponse
import com.bangkit.dewpet.data.response.UserResponse
import com.bangkit.dewpet.data.response.VetAppointmentStatusResponse
import com.bangkit.dewpet.databinding.ActivityHistoryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDateTime.parse
import java.time.format.DateTimeFormatter
import java.util.*

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding

    private lateinit var userPref: UserPref

    val PREF_NAME = "AUTH_PREF"
    val KEY_TOKEN = "key.token"

    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Appointment Status"

        sharedPref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        userPref = UserPref(this)
        var token : String? = sharedPref.getString(KEY_TOKEN, null)
        val request = RequestUser()
        request.token = token

        getUser(request)
    }

    private fun getUser(request: RequestUser){
        val retrofit = ApiConfig().getRetrofitClientInstance().create(ApiService::class.java)
        retrofit.getUser(request).enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val result = response.body()
                if (result != null){
                    Log.e("Email", result.email.toString())
                    val requestStatus = RequestAppointmentStatus()
                    requestStatus.client_email = result.email
                    getStatus(requestStatus)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }
        })
    }

    private fun getStatus(requestStatus: RequestAppointmentStatus){
        val retrofit = ApiConfig().getRetrofitClientInstance().create(ApiService::class.java)
        retrofit.statusAppointment(requestStatus).enqueue(object : Callback<VetAppointmentStatusResponse>{
            override fun onResponse(
                call: Call<VetAppointmentStatusResponse>,
                response: Response<VetAppointmentStatusResponse>
            ) {
                val result = response.body()
                if (result != null){
                    showData(result)
                }
            }

            override fun onFailure(call: Call<VetAppointmentStatusResponse>, t: Throwable) {
                Log.e("Error Status", t.message.toString())
            }
        })
    }

    private fun showData(data: VetAppointmentStatusResponse){
        val results = data.data
        for (i in results) {
            Log.e("Data", i.startAt.toString())
        }
    }
}