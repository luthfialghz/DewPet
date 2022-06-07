package com.bangkit.dewpet.activity.detail

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.dewpet.R
import com.bangkit.dewpet.databinding.ActivityDetailArticleBinding
import com.bangkit.dewpet.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_article.view.*

class DetailArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val urlPhoto = intent.getStringExtra("EXTRA_IMAGE")
        Glide.with(binding.root)
            .load(urlPhoto)
            .centerCrop()
            .into(binding.ivArticle)

        binding.tvTitle.text = intent.getStringExtra("EXTRA_TITLE")
        binding.tvContent.text = intent.getStringExtra("EXTRA_CONTENT")
    }
}