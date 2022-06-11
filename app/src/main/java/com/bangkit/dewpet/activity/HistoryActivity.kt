package com.bangkit.dewpet.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.dewpet.activity.detail.StatusDetailActivity
import com.bangkit.dewpet.adapter.ListStatusAdapter
import com.bangkit.dewpet.data.api.ApiConfig
import com.bangkit.dewpet.data.api.ApiService
import com.bangkit.dewpet.data.preferences.UserPref
import com.bangkit.dewpet.data.request.RequestAppointmentStatus
import com.bangkit.dewpet.data.request.RequestUser
import com.bangkit.dewpet.data.response.DeleteVetAppointmentStatusResponse
import com.bangkit.dewpet.data.response.UserResponse
import com.bangkit.dewpet.data.response.VetAppointmentStatusResponse
import com.bangkit.dewpet.databinding.ActivityHistoryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class HistoryActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    lateinit var listStatusAdapter: ListStatusAdapter

    private lateinit var userPref: UserPref

    val PREF_NAME = "AUTH_PREF"
    val KEY_TOKEN = "key.token"

    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        sharedPref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        binding.rvStatusAppointment.layoutManager = LinearLayoutManager(this)
        binding.rvStatusAppointment.setHasFixedSize(true)
        binding.ivBackButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        userPref = UserPref(this)
        var token : String? = sharedPref.getString(KEY_TOKEN, null)
        val request = RequestUser()
        request.token = token

        setupRecyclerView()
        getUser(request)
        startProg()
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

    private fun setupRecyclerView() {
        listStatusAdapter = ListStatusAdapter(arrayListOf(), object : ListStatusAdapter.onAdapterListener{

            override fun onClick(result: VetAppointmentStatusResponse.DataItem) {
                val id = result.id
                id?.let { deleteStatus(it) }
                recreate()
            }

            override fun onEdit(result: VetAppointmentStatusResponse.DataItem) {
                val dateInString = result.startAt.toString()
                val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
                val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.ENGLISH)
                val date = LocalDateTime.parse(dateInString, inputFormatter)
                val formattedDate = outputFormatter.format(date)
                startActivity(
                    Intent(applicationContext, StatusDetailActivity::class.java)
                        .putExtra("EXTRA_LOCATION", result.location)
                        .putExtra("EXTRA_DATE", formattedDate)
                        .putExtra("EXTRA_COMPLAINT", result.message)
                        .putExtra("EXTRA_VET_SERVICE", result.serviceId)
                        .putExtra("EXTRA_VET_STATUS", result.id)
                        .putExtra("EXTRA_VET_NAME", result.namaL)
                )
                finish()
            }

        })
        binding.rvStatusAppointment.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = listStatusAdapter
        }
    }

    private fun showData(data: VetAppointmentStatusResponse){
        val results = data.data
        listStatusAdapter.setData(results)
        stopProg()
    }

    private fun deleteStatus(id: Int) {
        val retrofit = ApiConfig().getRetrofitClientInstance().create(ApiService::class.java)
        retrofit.deleteAppointment(id).enqueue(object : Callback<DeleteVetAppointmentStatusResponse>{
            override fun onResponse(
                call: Call<DeleteVetAppointmentStatusResponse>,
                response: Response<DeleteVetAppointmentStatusResponse>
            ) {
                val result = response.body()?.status
                Log.e("Berhasil Delete", result.toString())
            }

            override fun onFailure(call: Call<DeleteVetAppointmentStatusResponse>, t: Throwable) {
                Log.e("Error Delete", t.message.toString())
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