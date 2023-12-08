package com.example.planetaria.epic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EpicViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EpicViewModel::class.java)){
            return EpicViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}