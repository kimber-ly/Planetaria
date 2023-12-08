package com.example.planetaria.epic

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planetaria.apiService.EpicDataModel
import com.example.planetaria.apiService.EpicRetrofit
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class EpicViewModel: ViewModel() {
    companion object{
        private const val API_KEY = "gwy76Uekb1BppLNfIcX7USj6d4oYVbdIcVNkfd4x"
    }

    private val _epicData = MutableLiveData<List<EpicDataModel>>()
    val epicData: LiveData<List<EpicDataModel>> = _epicData

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val apiService = EpicRetrofit.instance
                val response = apiService.getData(API_KEY).awaitResponse()

                if (response.isSuccessful){
                    _epicData.value = response.body()
                } else{
                    Log.d("$this@EpicViewModel", "Unsuccessful response: ${response.code()}")
                }
            }catch (e: Exception){
                Log.d("$this@EpicViewModel", "Exception: ${e.message}")
            }
        }
    }
}