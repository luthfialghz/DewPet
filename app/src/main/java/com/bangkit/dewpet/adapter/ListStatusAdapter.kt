package com.bangkit.dewpet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.dewpet.R
import com.bangkit.dewpet.data.response.VetAppointmentStatusResponse
import kotlinx.android.synthetic.main.row_status.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class ListStatusAdapter(val results : ArrayList<VetAppointmentStatusResponse.DataItem>, val listener: ListStatusAdapter.onAdapterListener) :
    RecyclerView.Adapter<ListStatusAdapter.ViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    )= ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.row_status, parent, false)
    )

    override fun onBindViewHolder(holder: ListStatusAdapter.ViewHolder, position: Int) {
        val result = results[position]
        holder.view.tv_vet_location.text = result.location
        holder.view.tv_vet_complaint.text = result.message
        holder.view.tv_vet_name.text = result.namaL
        val dateInString = result.startAt.toString()
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
        val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.ENGLISH)
        val date = LocalDateTime.parse(dateInString, inputFormatter)
        val formattedDate = outputFormatter.format(date)
        holder.view.tv_vet_date.text = formattedDate
        if (result.approved == "waiting"){
            holder.view.tv_vet_status.text = "Menunggu persetujuan dokter"
        } else if ( result.approved == "no"){
            holder.view.tv_vet_status.text = "Ditolak"
        } else if (result.approved == "yes"){
            holder.view.tv_vet_status.text = "Diterima"
        }
        holder.view.btn_cancel.setOnClickListener {
            listener.onClick(result)
        }
        holder.view.btn_edit.setOnClickListener {
            listener.onEdit(result)
        }
    }

    override fun getItemCount(): Int = results.size

    fun setData(data: List<VetAppointmentStatusResponse.DataItem>) {
        results.addAll( data )
        notifyDataSetChanged()
    }

    class ViewHolder (val view: View) : RecyclerView.ViewHolder(view)

    interface onAdapterListener {
        fun onClick(result : VetAppointmentStatusResponse.DataItem)
        fun onEdit(result: VetAppointmentStatusResponse.DataItem)
    }
}