package com.bangkit.dewpet.features

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.*
import android.os.AsyncTask
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.dewpet.R
import com.bangkit.dewpet.activity.MainActivity
import com.bangkit.dewpet.data.networking.ParserPlace
import com.bangkit.dewpet.databinding.ActivityDewCareBinding
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.json.JSONObject
import java.io.*
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import kotlin.collections.HashMap

class DewCareActivity : AppCompatActivity(), LocationListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityDewCareBinding
    var pBar: ProgressBar? = null
    var mLatitude = 0.0
    var mLongitude = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDewCareBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        binding.flBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        val spinnerCari = binding.spnCari
        pBar = binding.pBar

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment!!.getMapAsync{ googleMap ->
            mMap = googleMap
            initMap()
        }

        val myAdapter = ArrayAdapter(
            this@DewCareActivity,
            android.R.layout.simple_list_item_1, resources.getStringArray(R.array.nearby)
        )
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCari.adapter = myAdapter
        spinnerCari.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                l: Long
            ) {
                var xType = ""
                if (position == 1) xType = "pet_store" else if (position == 2) xType = "veterinary_care"
                if (position != 0) {
                    val sb =
                        "https://maps.googleapis.com/maps/api/place/nearbysearch/json?" +
                            "location=" + mLatitude + "," + mLongitude +
                            "&radius=5000" +
                            "&types=" + xType +
                            "&sensor=true" +
                            "&key=" + resources.getString(R.string.google_maps_key)
                    startProg()
                    PlacesTask().execute(sb)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        }

    private fun initMap() { //cek permission
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                115
            )
            return
        }
        if (mMap != null) {
            startProg()
            mMap!!.isMyLocationEnabled = true
            val locationManager =
                getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val criteria = Criteria()
            val provider = locationManager.getBestProvider(criteria, true)
            val location =
                locationManager.getLastKnownLocation(provider!!)
            if (location != null) {
                onLocationChanged(location)
            } else stopProg()
            locationManager.requestLocationUpdates(provider, 20000, 0f, this)
        }
    }

    override fun onLocationChanged(location: Location) {
        mLatitude = location.latitude
        mLongitude = location.longitude
        val latLng = LatLng(mLatitude, mLongitude)
        /*Circle circle = mGoogleMap.addCircle(new CircleOptions()
                .center(new LatLng(mLatitude, mLongitude))
                .radius(500)
                .strokeWidth(6)
                .strokeColor(0xffff0000)
                .fillColor(0x55ff0000));*/mMap!!.moveCamera(
            CameraUpdateFactory.newLatLng(
                latLng
            )
        )
        mMap!!.animateCamera(CameraUpdateFactory.zoomTo(12f))
        stopProg()
    }

    private fun startProg() {
        binding.pBar.visibility = View.VISIBLE
    }

    private fun stopProg() {
        binding.pBar.visibility = View.GONE
    }

    @SuppressLint("StaticFieldLeak")
    private inner class PlacesTask :
        AsyncTask<String?, Int?, String?>() {
        protected override fun doInBackground(vararg url: String?): String? {
            var data: String? = null
            try {
                data = downloadUrl(url[0].toString())
            } catch (e: Exception) {
                stopProg()
                e.printStackTrace()
            }
            return data
        }

        override fun onPostExecute(result: String?) {
            ParserTask().execute(result)
        }
    }

    private fun downloadUrl(strUrl: String): String {
        var data = ""
        val iStream: InputStream
        val urlConnection: HttpURLConnection
        try {
            val url = URL(strUrl)
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.connect()
            iStream = urlConnection.inputStream
            val br =
                BufferedReader(InputStreamReader(iStream))
            val sb = StringBuilder()
            var line: String?
            while (br.readLine().also { line = it } != null) {
                sb.append(line)
            }
            data = sb.toString()
            br.close()
            iStream.close()
            urlConnection.disconnect()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return data
    }

    @SuppressLint("StaticFieldLeak")
    private inner class ParserTask :
        AsyncTask<String?, Int?, List<java.util.HashMap<String, String>>?>() {
        var jObject: JSONObject? = null
        protected override fun doInBackground(vararg jsonData: String?): List<java.util.HashMap<String, String>>? {
            var places: List<java.util.HashMap<String, String>>? =
                null
            val parserPlace = ParserPlace()
            try {
                jObject = JSONObject(jsonData[0])
                places = parserPlace.parse(jObject!!)
            } catch (e: Exception) {
                stopProg()
                e.printStackTrace()
            }
            return places
        }

        //untuk menampilkan jumlah lokasi terdekat
        override fun onPostExecute(list: List<java.util.HashMap<String, String>>?) {
            mMap!!.clear()
            for (i in list!!.indices) {
                val markerOptions = MarkerOptions()
                val hmPlace = list[i]
                val pinDrop =
                    BitmapDescriptorFactory.fromResource(R.drawable.ic_pin)
                val lat = hmPlace["lat"]!!.toDouble()
                val lng = hmPlace["lng"]!!.toDouble()
                val nama = hmPlace["place_name"]
                val namaJln = hmPlace["vicinity"]
                val latLng = LatLng(lat, lng)
                markerOptions.icon(pinDrop)
                markerOptions.position(latLng)
                markerOptions.title("$nama : $namaJln")
                mMap!!.addMarker(markerOptions)
            }
            stopProg()
        }
    }

    override fun onStatusChanged(s: String, i: Int, bundle: Bundle) {}
    override fun onProviderEnabled(s: String) {}
    override fun onProviderDisabled(s: String) {}
}