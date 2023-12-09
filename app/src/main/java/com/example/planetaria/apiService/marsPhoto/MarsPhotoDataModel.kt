package com.example.planetaria.apiService.marsPhoto

import com.google.gson.annotations.SerializedName

data class MarsPhotoDataModel(
    @SerializedName("img_src") val image: String
)
