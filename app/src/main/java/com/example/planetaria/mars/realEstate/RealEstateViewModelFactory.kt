package com.example.planetaria.mars.realEstate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RealEstateViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RealEstateViewModel::class.java)){
            return RealEstateViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}