package com.bangkit.dewpet.features

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.dewpet.R
import com.bangkit.dewpet.adapter.ListArticleAdapter
import com.bangkit.dewpet.adapter.ListIndicationAdapter
import com.bangkit.dewpet.data.api.ApiConfig
import com.bangkit.dewpet.data.api.ApiService
import com.bangkit.dewpet.data.response.ArticleResponse
import com.bangkit.dewpet.data.response.IndicatorResponse
import com.bangkit.dewpet.databinding.ActivityDewTecBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DewTecActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDewTecBinding
    lateinit var listIndicationAdapter: ListIndicationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDewTecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvIndicator.layoutManager = LinearLayoutManager(this)
        binding.rvIndicator.setHasFixedSize(true)

        setupRecyclerView()
        getDisease()
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
                    showData(result)
                }
            }

            override fun onFailure(call: Call<IndicatorResponse>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }
        })
    }

    private fun setupRecyclerView() {
        listIndicationAdapter = ListIndicationAdapter(arrayListOf(), object : ListIndicationAdapter.onAdapterListener{
            override fun onClick(result: IndicatorResponse.GejalaItem) {
                val item = result.namaGejala
                Log.e("e", item.toString())
                val checkbox = binding.rvIndicator
//                if (checkbox.isSelected){
//                    val arrayIndicator: ArrayList<String>
//                    arrayIndicator.add()
//                }
            }

        })
        binding.rvIndicator.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = listIndicationAdapter
        }
    }

    private fun showData(data: IndicatorResponse){
        val results = data.gejala
        listIndicationAdapter.setData(results)
    }
}