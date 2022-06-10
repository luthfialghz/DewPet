package com.bangkit.dewpet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.dewpet.R
import com.bangkit.dewpet.data.response.AttachAppointmentStatusResponse
import kotlinx.android.synthetic.main.row_status.view.*
import kotlinx.android.synthetic.main.row_status_admin.view.*
import kotlinx.android.synthetic.main.row_status_admin.view.tv_vet_date
import kotlinx.android.synthetic.main.row_status_admin.view.tv_vet_name
import kotlinx.android.synthetic.main.row_status_admin.view.tv_vet_status
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class ListStatusAdminAdapter(val results : ArrayList<AttachAppointmentStatusResponse.DataItem>, val listener: ListStatusAdminAdapter.onAdapterListener): RecyclerView.Adapter<ListStatusAdminAdapter.ViewHolder>() {
    class ViewHolder (val view: View) : RecyclerView.ViewHolder(view)

    interface onAdapterListener {
        fun onApprove(result : AttachAppointmentStatusResponse.DataItem)
        fun onDecline(result: AttachAppointmentStatusResponse.DataItem)
    }

    fun setData(data: List<AttachAppointmentStatusResponse.DataItem>) {
        results.addAll( data )
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.row_status_admin, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        holder.view.tv_vet_name.text = result.namaL
        holder.view.tv_vet_status.text = result.approved
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
        holder.view.btn_accept.setOnClickListener {
            listener.onApprove(result)
        }
        holder.view.btn_decline.setOnClickListener {
            listener.onDecline(result)
        }
    }

    override fun getItemCount(): Int = results.size
}