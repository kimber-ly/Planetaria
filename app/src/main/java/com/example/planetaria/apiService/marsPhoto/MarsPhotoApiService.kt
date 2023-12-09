package com.example.planetaria.apiService.marsPhoto

import retrofit2.Call
import retrofit2.http.GET

interface MarsPhotoApiService {
    @GET("photos")
    fun getData(): Call<List<MarsPhotoDataModel>>
}