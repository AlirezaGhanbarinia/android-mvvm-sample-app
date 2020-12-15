package com.aliasadi.mvvm.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aliasadi.mvvm.data.network.services.MovieService

class MainViewModelFactory(private val movieService: MovieService) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(movieService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}