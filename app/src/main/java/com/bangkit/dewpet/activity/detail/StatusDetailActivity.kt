package com.bangkit.dewpet.activity.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bangkit.dewpet.R
import com.bangkit.dewpet.data.api.ApiConfig
import com.bangkit.dewpet.data.api.ApiService
import com.bangkit.dewpet.data.request.RequestEditAppointment
import com.bangkit.dewpet.data.response.EditVetAppointmentResponse
import com.bangkit.dewpet.data.response.UserResponse
import com.bangkit.dewpet.databinding.ActivityStatusDetailBinding
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class StatusDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStatusDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatusDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvVetLocation.text = intent.getStringExtra("EXTRA_LOCATION")

        val start_at = intent.getStringExtra("EXTRA_DATE")
        val message = "After Testing"
        val service_id = intent.getIntExtra("EXTRA_VET_SERVICE", 0)
        val id = intent.getIntExtra("EXTRA_VET_STATUS", 0)

        val request = RequestEditAppointment()
        request.id = id
        request.start_at = start_at
        request.message = message
        request.service_id = service_id
        Log.e("Service", service_id.toString())

        editStatus(request)
    }

    private fun editStatus(request: RequestEditAppointment){
        val retrofit = ApiConfig().getRetrofitClientInstance().create(ApiService::class.java)
        retrofit.editAppointment(request).enqueue(object : retrofit2.Callback<EditVetAppointmentResponse> {
            override fun onResponse(
                call: Call<EditVetAppointmentResponse>,
                response: Response<EditVetAppointmentResponse>
            ) {
                val result = response.body()?.status
                Log.e("Berhasil", result.toString())
            }

            override fun onFailure(call: Call<EditVetAppointmentResponse>, t: Throwable) {
                Log.e("Gagal", t.message.toString())
            }
        })
    }
}