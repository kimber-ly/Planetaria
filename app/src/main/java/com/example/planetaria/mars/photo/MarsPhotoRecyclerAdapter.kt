package com.example.planetaria.mars.photo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.planetaria.R
import com.example.planetaria.apiService.marsPhoto.MarsPhotoDataModel
import com.squareup.picasso.Picasso

class MarsPhotoRecyclerAdapter(private var photos: List<MarsPhotoDataModel>):
    RecyclerView.Adapter<MarsPhotoRecyclerAdapter.Viewholder>() {
    class Viewholder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.mars_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val context = parent.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.mars_photo_card, parent,false)
        return Viewholder(itemView)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val photosItem = photos[position]
        Picasso.get()
            .load(photosItem.image)
            .placeholder(R.drawable.baseline_image_24)
            .into(holder.image)
    }

    fun updateData(marsPhotoDataList: List<MarsPhotoDataModel>){
        photos = marsPhotoDataList
        notifyDataSetChanged()
    }
}