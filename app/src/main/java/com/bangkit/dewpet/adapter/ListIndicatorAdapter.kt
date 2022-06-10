package com.bangkit.dewpet.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.dewpet.R
import com.bangkit.dewpet.data.response.ArticleResponse
import com.bangkit.dewpet.data.response.IndicatorResponse
import com.bangkit.dewpet.features.DewTecActivity
import kotlinx.android.synthetic.main.activity_dew_tec.view.*
import kotlinx.android.synthetic.main.row_indicator.view.*

class ListIndicatorAdapter(val results : ArrayList<IndicatorResponse.GejalaItem>, val listener: ListIndicatorAdapter.onAdapterListener): RecyclerView.Adapter<ListIndicatorAdapter.ViewHolder>() {

    var mutableListIndicator = ArrayList<String>()
    var indicatorGejala = MutableLiveData<ArrayList<String>>()


    class ViewHolder (val view: View) : RecyclerView.ViewHolder(view)

    fun setData(data: List<IndicatorResponse.GejalaItem>) {
        results.addAll( data )
        notifyDataSetChanged()
    }

    fun getData(): MutableLiveData<ArrayList<String>>{
        return indicatorGejala
    }

    interface onAdapterListener {
        fun onClick(results : IndicatorResponse.GejalaItem)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    )= ListIndicatorAdapter.ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.row_indicator, parent, false)
        )

    override fun onBindViewHolder(holder: ListIndicatorAdapter.ViewHolder, position: Int) {
        val data = results[position]
        val indicatorName = data.namaGejala
        holder.view.cb_vet_indicator.text = indicatorName
        val indicator = holder.view.cb_vet_indicator

        indicator.setOnClickListener {
            if (indicator.isChecked){
                mutableListIndicator.add(indicatorName.toString())
                val mutableLiveData = mutableListIndicator
                indicatorGejala.value = mutableLiveData
            } else {
                indicatorGejala.value?.remove(indicatorName.toString())
            }
        }
    }

    override fun getItemCount(): Int = results.size
}