package com.bangkit.dewpet.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.dewpet.R
import com.bangkit.dewpet.data.response.VetResponse
import kotlinx.android.synthetic.main.row_vet.view.*

class ListVeterinaryAdapter(val results : ArrayList<VetResponse.JadwalItem>, val listener: ListVeterinaryAdapter.onAdapterListener)
    : RecyclerView.Adapter<ListVeterinaryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    )= ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.row_vet, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        holder.view.tv_name_vet.text = result.namaL
        holder.view.tv_location.text = result.location
        holder.view.tv_day.text = "Buka Hari    : ${result.day}"

        holder.view.tv_open.text = "Jam Buka    : ${result.open}"
        holder.view.tv_close.text = "Jam Tutup  : ${result.close}"
        holder.view.btn_call_vet.setOnClickListener {
            listener.onClick(result)
        }
        holder.view.btn_req_appointment.setOnClickListener {
            listener.reqAppointment(result)
        }
    }

    override fun getItemCount(): Int = results.size

    class ViewHolder (val view: View) : RecyclerView.ViewHolder(view)

    fun setData(data: List<VetResponse.JadwalItem>) {
        results.addAll( data )
        notifyDataSetChanged()
    }

    interface onAdapterListener {
        fun onClick(result : VetResponse.JadwalItem)
        fun reqAppointment(result: VetResponse.JadwalItem)
    }
}