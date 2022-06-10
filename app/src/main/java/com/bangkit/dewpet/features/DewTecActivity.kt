package com.bangkit.dewpet.features

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.dewpet.activity.MainActivity
import com.bangkit.dewpet.activity.detail.DetailDiagnoseActivity
import com.bangkit.dewpet.adapter.ListArticleAdapter
import com.bangkit.dewpet.adapter.ListIndicatorAdapter
import com.bangkit.dewpet.data.api.ApiConfig
import com.bangkit.dewpet.data.api.ApiService
import com.bangkit.dewpet.data.request.RequestDiagnose
import com.bangkit.dewpet.data.request.RequestLogin
import com.bangkit.dewpet.data.response.DiagnoseResponse
import com.bangkit.dewpet.data.response.IndicatorResponse
import com.bangkit.dewpet.databinding.ActivityDewTecBinding
import kotlinx.android.synthetic.main.activity_dew_tec.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DewTecActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDewTecBinding
    lateinit var listIndicatorAdapter: ListIndicatorAdapter
    val request = RequestDiagnose()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDewTecBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.rvIndicator.layoutManager = LinearLayoutManager(this)
        binding.rvIndicator.setHasFixedSize(true)

        binding.ivBackDiagnose.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        getDisease()
        setupRecyclerView()
    }

    private fun getDisease(){
        val retrofit = ApiConfig().getRetrofitClientInstance().create(ApiService::class.java)
        retrofit.listIndication().enqueue(object : Callback<IndicatorResponse>{
            override fun onResponse(
                call: Call<IndicatorResponse>,
                response: Response<IndicatorResponse>
            ) {
                val result = response.body()
                if (result != null){
                    showData(result.gejala)
                }
            }

            override fun onFailure(call: Call<IndicatorResponse>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }
        })
    }

    private fun setupRecyclerView() {
        listIndicatorAdapter = ListIndicatorAdapter(arrayListOf(), object : ListIndicatorAdapter.onAdapterListener{
            override fun onClick(results: IndicatorResponse.GejalaItem) {
            }

        })
        binding.rvIndicator.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = listIndicatorAdapter
        }
    }

    private fun showData(data: List<IndicatorResponse.GejalaItem>){
        listIndicatorAdapter.setData(data)
        val testing = listIndicatorAdapter.getData()
        testing.observe(this){
            request.penyakit = it
            Log.e("Data", it.toString())
            binding.btnDiagnose.setOnClickListener {
                if (rb_cat.isChecked) {
                    request.hewan = rb_cat.text.toString()
                } else if (rb_dog.isChecked){
                    request.hewan = rb_dog.text.toString()
                } else {
                    request.hewan = rb_rabbit.text.toString()
                }
                Log.e("Penyakit", request.penyakit.toString())
                Log.e("Hewan", request.hewan.toString())
                getDiagnose()
            }
        }
    }

    private fun getDiagnose() {
        val retrofit = ApiConfig().getRetrofitClientInstance().create(ApiService::class.java)
        retrofit.diagnose(request).enqueue(object : Callback<DiagnoseResponse>{
            override fun onResponse(
                call: Call<DiagnoseResponse>,
                response: Response<DiagnoseResponse>
            ) {
                if (response.body() != null){
                    val result = response.body()?.hasil
                    val category = response.body()?.kategori
                    val disease = "${result?.id} (Bahasa Indonesia)"
                    val diseaseEng = "${result?.en} (Bahasa Inggris)"
                    if (category == "Minor") {
                        val etc = "Dapat dirawat sendiri dengan obat obatan yang tersedia di PetCare"
                        val mCategory = "Penyakit Kategori Ringan"
                        intent(disease, mCategory, diseaseEng, etc)
                    } else {
                        val etc = "Segera Hubungi vet atau pergi ke PetCare"
                        val mCategory= "Penyakit Kategori Berat"
                        intent(disease, mCategory, diseaseEng, etc)
                    }
                }
            }

            private fun intent(disease: String?, category: String?, diseaseEng:String?, etc:String?){
                val intent = Intent(this@DewTecActivity, DetailDiagnoseActivity::class.java)
                    .putExtra("EXTRA_DISEASE", disease.toString())
                    .putExtra("EXTRA_CATEGORY", category.toString())
                    .putExtra("EXTRA_DISEASE_EN", diseaseEng.toString())
                    .putExtra("EXTRA_ETC", etc.toString())
                startActivity(intent)
                finish()
            }

            override fun onFailure(call: Call<DiagnoseResponse>, t: Throwable) {
                Log.e("Error Diagnose", t.message.toString())
            }
        })
    }
}