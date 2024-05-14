package com.sherina.shelterocation

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.util.*

class ShelterDetails : AppCompatActivity() {
    private lateinit var txtShelterNameDetail : TextView
    private lateinit var txtShelterAddressDetail : TextView
    private lateinit var txtShelterCityDetail : TextView
    private lateinit var ivShelterPhotoDetail : ImageView
    private lateinit var txtShelterPhoneDetail : TextView
    private lateinit var txtShelterOpenHoursDetail : TextView
    private lateinit var txtShelterCloseHoursDetail : TextView
    private lateinit var btnShelterMaps: TextView
    private lateinit var btnShare : TextView
    private lateinit var btnDonasi : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shelter_details)

        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        txtShelterNameDetail = findViewById(R.id.shelterNameDetail)
        txtShelterAddressDetail =findViewById(R.id.shelterAddressDetail)
        txtShelterCityDetail = findViewById(R.id.shelterCityDetail)
        ivShelterPhotoDetail = findViewById(R.id.tvShelterPhotoDetail)
        txtShelterPhoneDetail = findViewById(R.id.shelterPhoneDetail)
        txtShelterOpenHoursDetail = findViewById(R.id.shelterOpenHoursDetail)
        txtShelterCloseHoursDetail = findViewById(R.id.shelterCloseHoursDetail)
        btnShelterMaps = findViewById(R.id.shelterMaps)
        btnShare = findViewById(R.id.share)
        btnDonasi = findViewById(R.id.shelterDonation)

        val gambar = intent.getIntExtra("photo", 0)

        Glide.with(this)
            .load(gambar)
            .apply(RequestOptions().override(270, 270))
            .into(ivShelterPhotoDetail)

        txtShelterNameDetail.text = intent.getStringExtra("name")
        txtShelterAddressDetail.text = intent.getStringExtra("address")
        txtShelterCityDetail.text = intent.getStringExtra("city")
        txtShelterPhoneDetail.text = intent.getStringExtra("phone")
        txtShelterOpenHoursDetail.text = intent.getStringExtra("openHours")
        txtShelterCloseHoursDetail.text = intent.getStringExtra("closeHours")

        val upi = intent.getStringExtra("shareLink")


        actionBar?.title = intent.getStringExtra("name")

        btnShelterMaps.setOnClickListener {
            val intent2 = Intent(this, MapsActivity::class.java)
            intent2.setPackage("com.google.android.geo.API_KEY")
            startActivity(intent2)
        }

        btnShare.setOnClickListener {
            val intent3 = Intent(Intent.ACTION_SEND)
            intent3.type = "text/plain"
            intent3.putExtra(Intent.EXTRA_TEXT, upi.toString())
            startActivity(Intent.createChooser(intent3, "Share Using"))
        }

        btnDonasi.setOnClickListener{
            val intent4 = Intent(this, DonasiActivity::class.java)
            startActivity(intent4)
        }

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