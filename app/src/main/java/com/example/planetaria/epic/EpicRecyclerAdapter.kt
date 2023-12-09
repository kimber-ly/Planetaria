package com.example.planetaria.epic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.planetaria.R
import com.example.planetaria.apiService.epic.EpicDataModel
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Locale

class EpicRecyclerAdapter(private var items: List<EpicDataModel>):
    RecyclerView.Adapter<EpicRecyclerAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.epic_image)
        val date: TextView = itemView.findViewById(R.id.epic_date)
        val caption: TextView = itemView.findViewById(R.id.epic_caption)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.epic_card, parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val epicItem = items[position]

        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val date = dateFormat.parse(epicItem.date)
        val formattedDate = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(date!!)

        val baseUrl = "https://epic.gsfc.nasa.gov/archive/natural/${formattedDate}/png/"
        val fullUrl = "$baseUrl${epicItem.image}.png"

        Picasso.get()
            .load(fullUrl)
            .placeholder(R.drawable.baseline_image_24)
            .into(holder.image)
        holder.date.text = epicItem.date
        holder.caption.text = epicItem.caption
    }

    fun updateData(epicDataList: List<EpicDataModel>) {
        items = epicDataList
        notifyDataSetChanged()
    }
}