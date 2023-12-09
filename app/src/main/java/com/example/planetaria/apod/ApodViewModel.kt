package com.example.planetaria.apod

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planetaria.apiService.apod.ApodDataModel
import com.example.planetaria.apiService.apod.ApodRetrofit
import kotlinx.coroutines.launch
import retrofit2.awaitResponse


class ApodViewModel: ViewModel() {
    companion object{
        private const val API_KEY = "gwy76Uekb1BppLNfIcX7USj6d4oYVbdIcVNkfd4x"
    }

    private val _apodData = MutableLiveData<ApodDataModel>()
    val apodData: LiveData<ApodDataModel> = _apodData

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val apiService = ApodRetrofit.instance
                val response = apiService.getData(API_KEY).awaitResponse()

                if (response.isSuccessful){
                    _apodData.value = response.body()
                }
                else{
                    Log.d("$this@ApodViewModel", "Unsuccessful response: ${response.code()}")
                }

            }catch (e: Exception){
                Log.d("$this@ApodViewModel", "Exception: ${e.message}")
            }

        }
    }
}