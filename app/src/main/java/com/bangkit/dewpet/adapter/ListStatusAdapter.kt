package com.bangkit.dewpet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.dewpet.R
import com.bangkit.dewpet.data.response.ArticleResponse
import com.bangkit.dewpet.data.response.VetAppointmentStatusResponse
import com.bangkit.dewpet.data.response.VetResponse
import kotlinx.android.synthetic.main.row_status.view.*

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
        if (result.approved == "waiting"){
            holder.view.tv_vet_status.text = "Menunggu persetujuan dokter"
        }
        holder.view.btn_vet_delete.setOnClickListener {
            listener.onClick(result)
        }
        holder.view.btn_vet_edit.setOnClickListener {
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