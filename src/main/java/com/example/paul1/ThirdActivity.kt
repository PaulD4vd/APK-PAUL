package com.example.paul1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.paul1.databinding.ThirdActivityBinding

class ThirdActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ThirdActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ThirdActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi tombol Back
        val buttonGoBack: Button = findViewById(R.id.btnBack)
        buttonGoBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Ends the current activity
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Set a new marker at the specified coordinates
        val newLocation = LatLng(-7.7778, 110.3676)
        mMap.addMarker(MarkerOptions().position(newLocation).title("Marker in New Location"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation, 25f)) // Adjust zoom level if needed
    }
}
