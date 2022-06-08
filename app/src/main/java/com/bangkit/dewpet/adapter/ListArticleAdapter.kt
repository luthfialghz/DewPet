package com.bangkit.dewpet.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.dewpet.R
import com.bangkit.dewpet.data.response.ArticleResponse
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_article.view.*

class ListArticleAdapter(val results : ArrayList<ArticleResponse.ArticlesItem>, val listener: onAdapterListener)
    : RecyclerView.Adapter<ListArticleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.row_article, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        holder.view.tvTitle.text = result.title
        holder.view.tvSource.text = result.author
        holder.view.tvPublishedAt.text = result.publishedAt
        holder.view.tvDescription.text = result.description
        Log.e("Image", "result image : ${result.urlToImage}")
        Glide.with(holder.view)
            .load(result.urlToImage)
            .centerCrop()
            .into(holder.view.ivArticleImage)
        holder.view.setOnClickListener {
            listener.onClick(result)
        }
    }

    override fun getItemCount(): Int = results.size

    class ViewHolder (val view: View) : RecyclerView.ViewHolder(view)

    fun setData(data: List<ArticleResponse.ArticlesItem>) {
        results.addAll( data )
        notifyDataSetChanged()
    }

    interface onAdapterListener {
        fun onClick(result : ArticleResponse.ArticlesItem)
    }

}