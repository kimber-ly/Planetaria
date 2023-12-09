package com.example.planetaria.apiService.epic

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EpicApiService {
    @GET("images")
    fun getData(@Query("api_key") apiKey: String): Call<List<EpicDataModel>>
}