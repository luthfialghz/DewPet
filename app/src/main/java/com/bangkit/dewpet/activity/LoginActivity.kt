package com.bangkit.dewpet.activity

import android.content.Context
import android.content.Intent
import android.content.Intent.EXTRA_USER
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.bangkit.dewpet.data.api.ApiConfig
import com.bangkit.dewpet.data.api.ApiService
import com.bangkit.dewpet.data.preferences.UserPref
import com.bangkit.dewpet.data.request.RequestLogin
import com.bangkit.dewpet.data.response.LoginResponse
import com.bangkit.dewpet.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var userPref: UserPref

    val PREF_NAME = "AUTH_PREF"
    val KEY_TOKEN = "key.token"
    val KEY_NAME = "key.name"

    lateinit var sharedPref: SharedPreferences
    var isRemembered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        supportActionBar?.hide()
        userPref = UserPref(this)

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.buttonLogin.setOnClickListener(this)
        configurationSharedPreferences()
    }

    override fun onClick(v: View?) {
        val authEmail = binding.etEmail.text.toString().trim()
        val authPassword = binding.etPassword.text.toString()

        val request = RequestLogin()
        var token: String?
        var name: String?
        request.email = authEmail
        request.password = authPassword

        msg()

        val retrofit = ApiConfig().getRetrofitClientInstance().create(ApiService::class.java)
        retrofit.login(request).enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val user = response.body()
                if (user != null){
                    token = user.token
                    name = user.namaL
                    saveName(name.toString())
                    saveToken(token.toString())

                    Log.e("Token", "Berhasil disimpan")
                    Log.e("Token", token.toString())
                    Toast.makeText(this@LoginActivity, "Berhasil masuk sebagai ${user.namaL}", Toast.LENGTH_LONG).show()
                    val mIntent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(mIntent)
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, "E-mail atau password yang dimasukkan salah", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "E-mail atau password yang dimasukkan salah", Toast.LENGTH_LONG).show()
            }
        })

    }

    private fun configurationSharedPreferences(){
        val preferences = getSharedPreferences("AUTH_PREF", MODE_PRIVATE)
        isRemembered = preferences.getBoolean("CHECKBOX", false)
        if (isRemembered) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.buttonLogin.setOnClickListener(this)
    }

    private fun saveName(name: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_NAME, name)
        editor.apply()
    }

    private fun saveToken(token: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_TOKEN, token)
        editor.apply()
    }

    private fun msg(){
        val email : String = binding.etEmail.text.toString()
        val password: String = binding.etPassword.text.toString()
        when {
            email == "" -> {
                val msg = Toast.makeText(this, "Masukkan Email", Toast.LENGTH_LONG)
                msg.setGravity(Gravity.TOP, 0, 140)
                msg.show()
            }
            password == "" -> {
                val msg = Toast.makeText(this, "Masukkan Password", Toast.LENGTH_LONG)
                msg.setGravity(Gravity.TOP, 0, 140)
                msg.show()
            }
        }
    }
}