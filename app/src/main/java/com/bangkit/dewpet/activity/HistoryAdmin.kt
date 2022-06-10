package com.bangkit.dewpet.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.dewpet.R
import com.bangkit.dewpet.activity.detail.StatusDetailActivity
import com.bangkit.dewpet.adapter.ListStatusAdapter
import com.bangkit.dewpet.adapter.ListStatusAdminAdapter
import com.bangkit.dewpet.data.api.ApiConfig
import com.bangkit.dewpet.data.api.ApiService
import com.bangkit.dewpet.data.request.RequestUpdateStatus
import com.bangkit.dewpet.data.response.AttachAppointmentStatusResponse
import com.bangkit.dewpet.data.response.UpdateStatusResponse
import com.bangkit.dewpet.data.response.VetAppointmentStatusResponse
import com.bangkit.dewpet.databinding.ActivityHistoryAdminBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class HistoryAdmin : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryAdminBinding
    lateinit var listStatusAdminAdapter: ListStatusAdminAdapter
    var request = RequestUpdateStatus()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.rvStatusAppointment.layoutManager = LinearLayoutManager(this)
        binding.rvStatusAppointment.setHasFixedSize(true)

        getAppointment()
        setupRecyclerView()
    }

    private fun getAppointment() {
        val retrofit = ApiConfig().getRetrofitClientInstance().create(ApiService::class.java)
        retrofit.statusAppointment().enqueue(object : Callback<AttachAppointmentStatusResponse>{
            override fun onResponse(
                call: Call<AttachAppointmentStatusResponse>,
                response: Response<AttachAppointmentStatusResponse>
            ) {
                val result = response.body()
                if (result != null){
                    showData(result)
                }
            }

            override fun onFailure(call: Call<AttachAppointmentStatusResponse>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }
        })
    }

    private fun statusUpdate(id: Int, approved: RequestUpdateStatus){
        val retrofit = ApiConfig().getRetrofitClientInstance().create(ApiService::class.java)
        retrofit.statusUpdate(id, approved).enqueue(object : Callback<UpdateStatusResponse>{
            override fun onResponse(
                call: Call<UpdateStatusResponse>,
                response: Response<UpdateStatusResponse>
            ) {
                val result = response.body()
                val status = result?.status
                Log.e("Status", status.toString())
                recreate()
            }

            override fun onFailure(call: Call<UpdateStatusResponse>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }
        })
    }

    private fun setupRecyclerView() {
        listStatusAdminAdapter = ListStatusAdminAdapter(arrayListOf(), object : ListStatusAdminAdapter.onAdapterListener{
            override fun onApprove(result: AttachAppointmentStatusResponse.DataItem) {
                request.approved = "yes"
                val id = result.id
                statusUpdate(id,request)
            }

            override fun onDecline(result: AttachAppointmentStatusResponse.DataItem) {
                request.approved = "no"
                val id = result.id
                Log.e("id", id.toString())
                statusUpdate(id,request)
            }

        })
        binding.rvStatusAppointment.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = listStatusAdminAdapter
        }
    }

    private fun showData(data: AttachAppointmentStatusResponse){
        val results = data.data
        listStatusAdminAdapter.setData(results)
    }

}