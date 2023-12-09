package com.example.planetaria.apiService.marsRealEstate

import retrofit2.Call
import retrofit2.http.GET

interface RealEstateApiService {
    @GET("realestate")
    fun getData(): Call<List<RealEstateDataModel>>
}