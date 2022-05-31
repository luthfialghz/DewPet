package com.bangkit.dewpet.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bangkit.dewpet.data.api.ApiConfig
import com.bangkit.dewpet.data.api.ApiService
import com.bangkit.dewpet.data.request.RequestLogin
import com.bangkit.dewpet.data.response.LoginResponse
import com.bangkit.dewpet.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.buttonLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val authEmail = binding.etEmail.text.toString().trim()
        val authPassword = binding.etPassword.text.toString()

        val request = RequestLogin()
        request.email = authEmail
        request.password = authPassword

        val retrofit = ApiConfig().getRetrofitClientInstance().create(ApiService::class.java)
        retrofit.login(request).enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val user = response.body()
                if (user != null){
                    Log.e("onResponse: berhasil", user.role.toString())
                    Toast.makeText(this@LoginActivity, "Berhasil masuk sebagai ${user.namaL}", Toast.LENGTH_LONG).show()
                    Intent(this@LoginActivity, MainActivity::class.java).also {
                        startActivity(it)
                        finish()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "E-mail atau password yang dimasukkan salah", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "E-mail atau password yang dimasukkan salah", Toast.LENGTH_LONG).show()
            }
        })
    }
}