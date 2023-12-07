package com.example.planetaria.apod

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ApodViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApodViewModel::class.java)){
            return ApodViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}