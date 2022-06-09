package com.bangkit.dewpet.activity.detail

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import com.bangkit.dewpet.activity.HistoryActivity
import com.bangkit.dewpet.data.api.ApiConfig
import com.bangkit.dewpet.data.api.ApiService
import com.bangkit.dewpet.data.request.RequestEditAppointment
import com.bangkit.dewpet.data.response.EditVetAppointmentResponse
import com.bangkit.dewpet.databinding.ActivityStatusDetailBinding
import kotlinx.android.synthetic.main.activity_appointment_vet.*
import kotlinx.android.synthetic.main.activity_status_detail.*
import kotlinx.android.synthetic.main.row_status.*
import retrofit2.Call
import retrofit2.Response
import java.util.*

class StatusDetailActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: ActivityStatusDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatusDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvVetLocation.text = intent.getStringExtra("EXTRA_LOCATION")

        val start_at = intent.getStringExtra("EXTRA_DATE")
        binding.tvVetDate.text = start_at
        val message = intent.getStringExtra("EXTRA_COMPLAINT")
        binding.etMessages.setText(message, TextView.BufferType.EDITABLE)
        val service_id = intent.getIntExtra("EXTRA_VET_SERVICE", 0)
        val id = intent.getIntExtra("EXTRA_VET_STATUS", 0)

        val request = RequestEditAppointment()
        request.id = id
        request.service_id = service_id
        Log.e("Service", service_id.toString())

        pickDate()
        binding.btnSubmitChange.setOnClickListener {
            val date = binding.tvVetDate.text.toString()
            val etMessage = binding.etMessages.text.toString()
            request.start_at = date
            request.message = etMessage
            editStatus(request)
        }
    }

    private fun pickDate() {
        btn_reschedule.setOnClickListener {
            val datePicker = DatePickerDialog(this,this, year, month, day)
            datePicker.datePicker.setMinDate(cal.timeInMillis)
            datePicker.show()

        }
    }

    private fun getDateTimeCalendar() {
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
    }

    private fun editStatus(request: RequestEditAppointment){
        val retrofit = ApiConfig().getRetrofitClientInstance().create(ApiService::class.java)
        retrofit.editAppointment(request).enqueue(object : retrofit2.Callback<EditVetAppointmentResponse> {
            override fun onResponse(
                call: Call<EditVetAppointmentResponse>,
                response: Response<EditVetAppointmentResponse>
            ) {
                val result = response.body()?.status
                Log.e("Berhasil", result.toString())
                val intent = Intent(this@StatusDetailActivity, HistoryActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onFailure(call: Call<EditVetAppointmentResponse>, t: Throwable) {
                Log.e("Gagal", t.message.toString())
            }
        })
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

        binding.tvVetDate.text = "${myYear}-${myMonth}-${myDay} ${myHour}:${myMinute}:00"
    }

    companion object {
        val cal = Calendar.getInstance()

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
}