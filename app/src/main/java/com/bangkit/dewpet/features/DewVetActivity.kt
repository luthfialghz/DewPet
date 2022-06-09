package com.bangkit.dewpet.features

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.dewpet.activity.MainActivity
import com.bangkit.dewpet.activity.detail.AppointmentVetActivity
import com.bangkit.dewpet.adapter.ListVeterinaryAdapter
import com.bangkit.dewpet.data.api.ApiConfig
import com.bangkit.dewpet.data.api.ApiService
import com.bangkit.dewpet.data.response.VetResponse
import com.bangkit.dewpet.databinding.ActivityDewVetBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DewVetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDewVetBinding
    lateinit var veterinaryAdapter: ListVeterinaryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDewVetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.ivBackButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        setupPermissions()
        setupRecyclerView()
        getVet()
    }

    private fun setupRecyclerView() {
        veterinaryAdapter = ListVeterinaryAdapter(arrayListOf(), object : ListVeterinaryAdapter.onAdapterListener{
            override fun onClick(result: VetResponse.JadwalItem) {
                val phoneNumber = result.numberHp
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }

            override fun reqAppointment(result: VetResponse.JadwalItem) {
                startActivity(Intent(this@DewVetActivity, AppointmentVetActivity::class.java)
                    .putExtra("EXTRA_VET_ID", result.serviceId)
                    .putExtra("EXTRA_VET_NAME", result.namaL)
                    .putExtra("EXTRA_VET_LOCATION", result.location)
                    .putExtra("EXTRA_VET_DAY", result.day)
                    .putExtra("EXTRA_VET_OPEN", result.open)
                    .putExtra("EXTRA_VET_CLOSE", result.close)
                )
            }

        })
        binding.rvRowVet.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = veterinaryAdapter
        }
    }

    private fun getVet() {
        val retrofit = ApiConfig().getRetrofitClientInstance().create(ApiService::class.java)
        retrofit.vetSchedule().enqueue(object : Callback<VetResponse> {
            override fun onResponse(call: Call<VetResponse>, response: Response<VetResponse>) {
                val result = response.body()
                if (result != null) {
                    showData(result)
                }
            }

            override fun onFailure(call: Call<VetResponse>, t: Throwable) {
                printLog(t.message.toString())
            }
        })
    }

    private fun printLog(message: String) {
        Log.e(MainActivity.TAG, message)
    }

    private fun showData(data: VetResponse){
        val results = data.jadwal
        veterinaryAdapter.setData(results)
    }

    fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i("Error", "Permission to call denied")
        }
    }

    companion object {
        const val TAG = "DewConsul"
    }
}