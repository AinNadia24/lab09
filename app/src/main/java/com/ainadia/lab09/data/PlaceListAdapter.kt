package com.ainadia.lab09.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ainadia.lab09.R
import com.ainadia.lab09.model.Place

// 2) Step 21 ()-> Passed as argument -> We will pass arraylist of Place and context
// 3) Step 22 -> Alt + Enter -> Implement methods -> OK (Contract -> Interface)
// To use a RecyclerView, we need to have an adapter
// An adapter is the link between the data set (ArrayList<Place>) and the UI (RecyclerView)

// To create an adapter, we need to implement RecyclerView.Adapter (Interface) - Context
// We need to implement 3 methods = onCreateCustomViewHolder, getItemCount, onBindViewHolder
class PlaceListAdapter(private val list: ArrayList<Place>, private val context: Context) : RecyclerView.Adapter<PlaceListAdapter.ViewHolder>() {

    // 1) Step 27

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    }

    // Which xml file will be used to show the ViewHolder -> custom_row
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // 5 step 29
        val view = LayoutInflater.from(context).inflate(R.layout.custom_row, parent, false)
        return ViewHolder(view)
    }

    //How many row are there in the recyclerView -> Bergantung kepada data
    // Jika ada 5 data -> 5 row
    override fun getItemCount(): Int {
        //4) step 28
        return list.size
    }

    //What to show in the customRow -> titleTextView, subtitlteTextView
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var countryTextView = holder.itemView.findViewById<TextView>(R.id.titleTextView)
        var cityTextView = holder.itemView.findViewById<TextView>(R.id.subtitleTextView) // r.id = kene sama dgn id declare di custom_row.xml
        countryTextView.text = list[position].countryName
        cityTextView.text = list[position].cityName

        }
}