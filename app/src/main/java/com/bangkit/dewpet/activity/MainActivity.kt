package com.bangkit.dewpet.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.dewpet.adapter.ListArticleAdapter
import com.bangkit.dewpet.data.api.ApiConfig
import com.bangkit.dewpet.data.api.ApiService
import com.bangkit.dewpet.data.preferences.UserPref
import com.bangkit.dewpet.data.request.RequestUser
import com.bangkit.dewpet.data.response.ArticleResponse
import com.bangkit.dewpet.data.response.UserResponse
import com.bangkit.dewpet.databinding.ActivityMainBinding
import com.bangkit.dewpet.features.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var listArticleAdapter: ListArticleAdapter
    private lateinit var userPref: UserPref

    val PREF_NAME = "AUTH_PREF"
    val KEY_TOKEN = "key.token"
    val KEY_NAME = "key.name"

    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        supportActionBar?.hide()
        userPref = UserPref(this)

        val request = RequestUser()
        var token : String? = sharedPref.getString(KEY_TOKEN, null)
        request.token = token

        binding.rvArticles.layoutManager = LinearLayoutManager(this)
        binding.rvArticles.setHasFixedSize(true)

        val profileName : String? = sharedPref.getString(KEY_NAME, null)
        Log.e("Homepage", profileName.toString())
        binding.tvNameUser.text = profileName.toString()


        binding.ivDewConsul.setOnClickListener{
            val intent = Intent(this, DewVetActivity::class.java)
            startActivity(intent)
        }

        binding.ivSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.ivDewCare.setOnClickListener {
            val intent = Intent(this, DewCareActivity::class.java)
            startActivity(intent)
        }

        binding.ivDewStore.setOnClickListener {
            val intent = Intent(this, DewStoreActivity::class.java)
            startActivity(intent)
        }

        binding.ivDewTec.setOnClickListener {
            val intent = Intent(this, DewTecActivity::class.java)
            startActivity(intent)
        }

        getUser(request)
    }

    override fun onStart() {
        super.onStart()
        setupRecyclerView()
        startProg()
        getArticle()
    }

    private fun setupRecyclerView() {
        listArticleAdapter = ListArticleAdapter(arrayListOf(), object : ListArticleAdapter.onAdapterListener{
            override fun onClick(result: ArticleResponse.ArticlesItem) {
                val openURL = Intent(android.content.Intent.ACTION_VIEW)
                openURL.data = Uri.parse(result.url.toString())
                startActivity(openURL)
            }

        })
        binding.rvArticles.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = listArticleAdapter
        }
    }

    private fun getArticle() {
        pBar.visibility = View.VISIBLE
        val retrofit = ApiConfig().getRetrofitClientInstance().create(ApiService::class.java)
        retrofit.articles().enqueue(object : Callback<ArticleResponse> {
            override fun onResponse(
                call: Call<ArticleResponse>,
                response: Response<ArticleResponse>
            ) {
                startProg()
                val result = response.body()
                if (result != null) {
                    showData(result)
                }
            }

            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                startProg()
                printLog(t.message.toString())
            }
        })
    }

    private fun getUser(request: RequestUser) {
        val retrofit = ApiConfig().getRetrofitClientInstance().create(ApiService::class.java)
        retrofit.getUser(request).enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val data = response.body()
                val role = data?.role
                if (role == "user") {
                    intentByUser()
                } else {
                    intentByAdmin()
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                printLog(t.message.toString())
            }
        })
    }

    private fun intentByUser() {
        binding.ivDewAdopt.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun intentByAdmin() {
        binding.ivDewAdopt.setOnClickListener {
            val intent = Intent(this, HistoryAdmin::class.java)
            startActivity(intent)
        }
    }

    private fun printLog(message: String) {
        Log.e(TAG, message)
    }

    private fun showData(data: ArticleResponse){
        val results = data.articles
        listArticleAdapter.setData(results)
        stopProg()
    }

    private fun startProg() {
        binding.pBar.visibility = View.VISIBLE
    }

    private fun stopProg() {
        binding.pBar.visibility = View.GONE
    }


    companion object {
        const val TAG = "MainActivity"
    }
}