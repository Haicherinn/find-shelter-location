package com.sherina.shelterocation

import android.app.DownloadManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.util.*
import kotlin.collections.ArrayList


class ListShelterAdapter(val listShelter : ArrayList<Shelter>) : RecyclerView.Adapter<ListShelterAdapter.ViewHolder>(), Filterable {
    var listShelterFilter : ArrayList<Shelter> = ArrayList()
    init {
        listShelterFilter = listShelter
    }

    interface OnItemClick {
        fun onItemClicked(data: Shelter)
    }

    private lateinit var onItemClick : OnItemClick

    fun setOnItemClick(onItemClick: OnItemClick){
        this.onItemClick = onItemClick
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var txtShelterPhoto : ImageView = itemView.findViewById(R.id.tvShelterPhoto)
        var txtShelterName : TextView = itemView.findViewById(R.id.tvShelterName)
        var txtShelterCity : TextView = itemView.findViewById(R.id.tvShelterCity)
        var txtShelterOpenHours : TextView = itemView.findViewById(R.id.tvOpenHoursShelter)
        var txtShelterCloseHours : TextView = itemView.findViewById(R.id.tvCloseHoursShelter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_list_shelter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shelter = listShelterFilter[position]

        Glide.with(holder.itemView.context)
            .load(shelter.shelterPhoto)
            .apply(RequestOptions().override(90, 90))
            .into(holder.txtShelterPhoto)

        holder.txtShelterName.text = shelter.shelterName.trim()
        holder.txtShelterCity.text = shelter.shelterCity.trim()
        holder.txtShelterOpenHours.text = shelter.shelterOpenHours.trim()
        holder.txtShelterCloseHours.text = shelter.shelterCloseHours.trim()

        holder.itemView.setOnClickListener {
            onItemClick.onItemClicked(listShelterFilter[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return listShelterFilter.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                charString.trim()
                if(charString.isEmpty()){
                    listShelterFilter = listShelter
                } else {
                    val resultList = ArrayList<Shelter>()

                    listShelter.filter {
                        (it.shelterName.toLowerCase().contains(constraint!!.trim())) or (it.shelterCity.toLowerCase().contains(constraint.trim()))
                    }.forEach{
                        resultList.add(it)
                    }
                    listShelterFilter = resultList

                }
                return FilterResults().apply { values = listShelterFilter }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                listShelterFilter = if(results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<Shelter>
                notifyDataSetChanged()
            }
        }
    }


}