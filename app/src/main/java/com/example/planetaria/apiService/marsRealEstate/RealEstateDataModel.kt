package com.example.planetaria.apiService.marsRealEstate

import com.google.gson.annotations.SerializedName

data class RealEstateDataModel(
    @SerializedName("img_src") val image: String,
    val id: String,
    val type: String,
    val price: Int,
)
