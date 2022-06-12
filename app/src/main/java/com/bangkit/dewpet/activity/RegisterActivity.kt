package com.bangkit.dewpet.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bangkit.dewpet.data.api.ApiConfig
import com.bangkit.dewpet.data.api.ApiService
import com.bangkit.dewpet.data.request.RequestRegister
import com.bangkit.dewpet.data.response.RegisterResponse
import com.bangkit.dewpet.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnBack.setOnClickListener {
            onBackPressed()
            finish()
        }

        binding.btnLogin.setOnClickListener {
            Intent(this@RegisterActivity, LoginActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.button.setOnClickListener {
            register()
        }
    }

    private fun register() {
        val authName = binding.etName.text.toString()
        val authEmail = binding.etEmail.text.toString()
        val authPassword = binding.etPassword.text.toString()

        val request = RequestRegister()
        request.name = authName
        request.email = authEmail
        request.password = authPassword

        val retrofit = ApiConfig().getRetrofitClientInstance().create(ApiService::class.java)
        retrofit.register(request).enqueue(object : retrofit2.Callback<RegisterResponse>{
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                val user = response.body()
                if (user != null){
                    Log.e("onResponse: berhasil", user.status.toString())
                    Toast.makeText(this@RegisterActivity, "Akun berhasil dibuat",Toast.LENGTH_SHORT).show()
                    Intent(this@RegisterActivity, LoginActivity::class.java).also {
                        startActivity(it)
                    }
                } else {
                    Toast.makeText(this@RegisterActivity, "E-mail yang anda masukkan telah digunakan",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
            }
        })
    }
}