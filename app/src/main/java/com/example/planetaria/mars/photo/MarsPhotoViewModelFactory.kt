package com.example.planetaria.mars.photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MarsPhotoViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MarsPhotoViewModel::class.java)){
            return MarsPhotoViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}