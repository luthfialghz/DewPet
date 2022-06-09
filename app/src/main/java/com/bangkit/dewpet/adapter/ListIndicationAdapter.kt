package com.bangkit.dewpet.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.core.app.ActivityCompat.recreate
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.dewpet.R
import com.bangkit.dewpet.data.response.ArticleResponse
import com.bangkit.dewpet.data.response.IndicatorResponse
import kotlinx.android.synthetic.main.activity_dew_tec.view.*
import kotlinx.android.synthetic.main.row_indicator.view.*

class ListIndicationAdapter(val results : ArrayList<IndicatorResponse.GejalaItem>, val listener: ListIndicationAdapter.onAdapterListener) : RecyclerView.Adapter<ListIndicationAdapter.ViewHolder>(){

    var arrayIndicator: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    )= ListIndicationAdapter.ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.row_indicator, parent, false)
    )

    override fun onBindViewHolder(holder: ListIndicationAdapter.ViewHolder, position: Int) {
        val result = results[position]
        holder.view.cb_vet_indicator.text = result.namaGejala.toString()
        val checkbox = holder.view.cb_vet_indicator
        checkbox.setOnClickListener {
            if (checkbox.isChecked){
                arrayIndicator.add(result.namaGejala.toString())
                Log.e("array", arrayIndicator.toString())
                arrayIndicator.distinct()
            }
        }

//        holder.view.btn_diagnose.setOnClickListener {
//            listener.onClick(result)
//        }
    }

    override fun getItemCount(): Int = results.size

    class ViewHolder (val view: View) : RecyclerView.ViewHolder(view)

    interface onAdapterListener {
        fun onClick(result : IndicatorResponse.GejalaItem)
    }

    fun setData(data: List<IndicatorResponse.GejalaItem>) {
        results.addAll( data )
        notifyDataSetChanged()
    }
}