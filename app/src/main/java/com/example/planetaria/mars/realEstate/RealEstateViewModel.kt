package com.example.planetaria.mars.realEstate

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planetaria.apiService.marsRealEstate.RealEstateDataModel
import com.example.planetaria.apiService.marsRealEstate.RealEstateRetrofit
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class RealEstateViewModel: ViewModel() {
    private val _data = MutableLiveData<List<RealEstateDataModel>>()
    val data : LiveData<List<RealEstateDataModel>> = _data

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val apiService = RealEstateRetrofit.instance
                val response = apiService.getData().awaitResponse()

                if (response.isSuccessful){
                    _data.value = response.body()
                }
                else {
                    Log.d("$this@RealEstateViewModel", "Unsuccessful response: ${response.code()}")
                }
            }catch (e: Exception){
                Log.d("$this@RealEstateViewModel", "Exception: ${e.message}")
            }
        }
    }
}