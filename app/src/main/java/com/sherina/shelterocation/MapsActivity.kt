package com.sherina.shelterocation

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.sherina.shelterocation.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Maps"

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val pejaten = LatLng(-6.276929, 106.825300)
        mMap.addMarker(MarkerOptions().position(pejaten).title("Pejaten Shelter "))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pejaten,10f))

        val anidef = LatLng( -6.166731, 106.911850)
        mMap.addMarker(MarkerOptions().position(anidef).title("Animal Defenders Adoption Center"))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(anidef,10f))

        val kenimal = LatLng(-6.275927, 106.818416)
        mMap.addMarker(MarkerOptions().position(pejaten).title("Kenimal Animal Shelter and rescue"))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(kenimal,10f))

        val animalcj = LatLng(-6.275316, 106.829939)
        mMap.addMarker(MarkerOptions().position(animalcj).title("Animal Clinic Jakarta"))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(animalcj,10f))

        val jakpetz = LatLng(-6.272955, 106.815965)
        mMap.addMarker(MarkerOptions().position(jakpetz).title("Jakpetz"))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(jakpetz,10f))

        val petlove = LatLng(-6.241775, 106.801752)
        mMap.addMarker(MarkerOptions().position(petlove).title("Petlove Center"))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(petlove,10f))

        val petcarez = LatLng(-6.200442, 106.882244)
        mMap.addMarker(MarkerOptions().position(petcarez).title("Pet Care Zone Of Natscat Cattery"))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(petcarez,10f))

        val petvet = LatLng(-6.205345, 106.816375)
        mMap.addMarker(MarkerOptions().position(petvet).title("Pet Vet"))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(petvet,10f))

        val jakartavetc = LatLng(-6.164176, 106.810874)
        mMap.addMarker(MarkerOptions().position(jakartavetc).title("Jakarta Vet Cideng"))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(jakartavetc,10f))

        val jakpetcen = LatLng(-6.119690, 106.787844)
        mMap.addMarker(MarkerOptions().position(jakpetcen).title("Jakarta Pet Care Center"))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(jakpetcen,10f))

        val natpet = LatLng( -6.166731, 106.911850)
        mMap.addMarker(MarkerOptions().position(natpet).title("NATASHA GADING KENNEL"))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(natpet,10f))

        val ranicat = LatLng( -6.166731, 106.911850)
        mMap.addMarker(MarkerOptions().position(ranicat).title("Rani's Cat House"))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ranicat,10f))

        val ragunan = LatLng(  -6.299136, 106.819450)
        mMap.addMarker(MarkerOptions().position(ragunan).title("Rumah Sakit Hewan Jakarta"))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ragunan,10f))

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}