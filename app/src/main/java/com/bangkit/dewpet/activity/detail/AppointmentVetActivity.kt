package com.bangkit.dewpet.activity.detail

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.widget.*
import com.bangkit.dewpet.R
import com.bangkit.dewpet.activity.MainActivity
import com.bangkit.dewpet.data.api.ApiConfig
import com.bangkit.dewpet.data.api.ApiService
import com.bangkit.dewpet.data.preferences.UserPref
import com.bangkit.dewpet.data.request.RequestAppointment
import com.bangkit.dewpet.data.request.RequestLogin
import com.bangkit.dewpet.data.request.RequestUser
import com.bangkit.dewpet.data.response.ArticleResponse
import com.bangkit.dewpet.data.response.UserResponse
import com.bangkit.dewpet.data.response.VetAppointmentResponse
import com.bangkit.dewpet.databinding.ActivityAppointmentVetBinding
import kotlinx.android.synthetic.main.activity_appointment_vet.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class AppointmentVetActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: ActivityAppointmentVetBinding

    lateinit var sharedPref: SharedPreferences
    private lateinit var userPref: UserPref
    val PREF_NAME = "AUTH_PREF"
    val KEY_TOKEN = "key.token"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppointmentVetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        userPref = UserPref(this)

        var token : String? = sharedPref.getString(KEY_TOKEN, null)

        val request = RequestUser()
        Log.e("token", token.toString())
        request.token = token

        pickDate()
        initializeVet()

        binding.btnSubmit.setOnClickListener {
            getData(request)
        }
    }

    private fun pickDate() {
        btn_date.setOnClickListener {
            getDateTimeCalendar()
            DatePickerDialog(this,this, year, month, day).show()

        }
    }

    private fun getDateTimeCalendar() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
    }

    private fun getData(request: RequestUser){
        val retrofit = ApiConfig().getRetrofitClientInstance().create(ApiService::class.java)
        retrofit.getUser(request).enqueue(object : Callback<UserResponse>{

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val etMessage = binding.etMessages.text.toString()
                val date = binding.tvDatePicker.text.toString()
                val serviceId = intent.getIntExtra("EXTRA_VET_ID", 0)
                val result = response.body()
                val requestAppointment = RequestAppointment()
                requestAppointment.client_email = result?.email
                requestAppointment.message = etMessage
                requestAppointment.service_id = serviceId
                requestAppointment.start_at = date
                Log.e("Check Date", date)
                makeAppointment(requestAppointment)
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("Error ", t.message.toString())
            }
        })
    }

    private fun makeAppointment(requestAppointment: RequestAppointment) {
        val retrofit = ApiConfig().getRetrofitClientInstance().create(ApiService::class.java)
        retrofit.reqAppointment(requestAppointment).enqueue(object : Callback<VetAppointmentResponse>{
            override fun onResponse(
                call: Call<VetAppointmentResponse>,
                response: Response<VetAppointmentResponse>
            ) {
                val result = response.body()
                Log.e("Success", result.toString())
                Toast.makeText(this@AppointmentVetActivity, "Berhasil membuat janji temu", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@AppointmentVetActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onFailure(call: Call<VetAppointmentResponse>, t: Throwable) {
                Log.e("Error on Appointment", t.message.toString())
            }
        })
    }


    private fun initializeVet(){
        val vetName = intent.getStringExtra("EXTRA_VET_NAME")
        val vetLocation = intent.getStringExtra("EXTRA_VET_LOCATION")
        val vetDay = intent.getStringExtra("EXTRA_VET_DAY")
        val vetOpen = intent.getStringExtra("EXTRA_VET_OPEN")
        val vetClose = intent.getStringExtra("EXTRA_VET_CLOSE")

        binding.tvVetName.text = vetName
        binding.tvVetLocation.text = vetLocation
        binding.tvVetDay.text = vetDay
        binding.tvVetOpen.text = vetOpen
        binding.tvVetClose.text = vetClose
    }

    companion object {
        var day = 0
        var month: Int = 0
        var year: Int = 0
        var hour: Int = 0
        var minute: Int = 0

        var myDay = 0
        var myMonth: Int = 0
        var myYear: Int = 0
        var myHour: Int = 0
        var myMinute: Int = 0

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        myDay = dayOfMonth
        myMonth = month
        myYear = year

        getDateTimeCalendar()

        TimePickerDialog(this,this, hour, minute,true).show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        myHour = hourOfDay
        myMinute = minute

        tv_date_picker.text = "$myYear-$myMonth-$myDay $myHour:$myMinute:00"
    }
}
