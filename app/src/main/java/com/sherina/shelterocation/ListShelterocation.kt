package com.sherina.shelterocation

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

class ListShelterocation : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var rvShelter : RecyclerView
    private var list : ArrayList<Shelter> = arrayListOf()
    private lateinit var searchView : SearchView
    private lateinit var listShelterAdapter: ListShelterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_shelterocation)
        rvShelter = findViewById(R.id.recyclerview)
        rvShelter.setHasFixedSize(true)
        list.addAll(ShelterData.listShelter)
        showRecyclerList()

        searchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(this)

        var filter = findViewById<ImageButton>(R.id.filter)
        filter.setOnClickListener {
            Toast.makeText(this, "Fitur akan segera hadir!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun showRecyclerList() {
        rvShelter.layoutManager = LinearLayoutManager(this)
        listShelterAdapter = ListShelterAdapter(list)
        rvShelter.adapter = listShelterAdapter


        listShelterAdapter.setOnItemClick(object : ListShelterAdapter.OnItemClick{
            override fun onItemClicked(data: Shelter) {
                intentData(data)
            }
        })
    }

    private fun intentData(shelter : Shelter){
        val intent = Intent(this, ShelterDetails::class.java)
        intent.putExtra("name", shelter.shelterName)
        intent.putExtra("photo", shelter.shelterPhoto)
        intent.putExtra("address", shelter.shelterAddress)
        intent.putExtra("city", shelter.shelterCity)
        intent.putExtra("openHours", shelter.shelterOpenHours)
        intent.putExtra("closeHours", shelter.shelterCloseHours)
        intent.putExtra("phone", shelter.shelterPhone)
        intent.putExtra("linkMaps", shelter.shelterMaps)
        intent.putExtra("shareLink", shelter.shelterShare)
        intent.putExtra("donasi", shelter.shelterDonation)
        startActivity(intent)
    }


    private fun intentAbout(){
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super .onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.about -> intentAbout()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        listShelterAdapter.filter.filter(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        listShelterAdapter.filter.filter(newText)
        return false
    }


}