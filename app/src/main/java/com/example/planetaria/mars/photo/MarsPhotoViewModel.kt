package com.example.planetaria.mars.photo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planetaria.apiService.marsPhoto.MarsPhotoDataModel
import com.example.planetaria.apiService.marsPhoto.MarsPhotoRetrofit
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class MarsPhotoViewModel: ViewModel() {
    private val _photos = MutableLiveData<List<MarsPhotoDataModel>>()
    val photos: LiveData<List<MarsPhotoDataModel>> = _photos

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val apiService = MarsPhotoRetrofit.instance
                val response = apiService.getData().awaitResponse()

                if (response.isSuccessful){
                    _photos.value = response.body()
                }
                else{
                    Log.d("$this@MarsPhotoViewModel", "Unsuccessful response: ${response.code()}")
                }
            }catch (e: Exception){
                Log.d("$this@MarsPhotoViewModel", "Exception: ${e.message}")
            }
        }
    }

}