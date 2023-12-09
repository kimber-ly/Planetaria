package com.example.planetaria.mars.realEstate

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.planetaria.R
import com.example.planetaria.apiService.marsRealEstate.RealEstateDataModel
import com.squareup.picasso.Picasso

class RealEstateRecyclerAdapter(private var items: List<RealEstateDataModel>):
    RecyclerView.Adapter<RealEstateRecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.real_estate_image)
        val id: TextView = itemView.findViewById(R.id.real_estate_id)
        val type: TextView = itemView.findViewById(R.id.real_estate_type)
        val price: TextView = itemView.findViewById(R.id.real_estate_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.real_estate_card, parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataItem = items[position]

        val httpsImageUrl = dataItem.image.replace("http://", "https://")

        Log.d("Image URL", httpsImageUrl)
        Picasso.get()
            .load(httpsImageUrl)
            .placeholder(R.drawable.baseline_image_24)
            .into(holder.image)

        holder.id.text = dataItem.id
        holder.type.text = dataItem.type
        holder.price.text = dataItem.price.toString()
    }

    fun updateData(realEstateDataList: List<RealEstateDataModel>){
        items = realEstateDataList
        notifyDataSetChanged()
    }

}