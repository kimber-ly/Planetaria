package com.example.planetaria.apiService.apod

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodApiService {
    @GET("apod")
    fun getData(@Query("api_key") apiKey: String): Call<ApodDataModel>
}