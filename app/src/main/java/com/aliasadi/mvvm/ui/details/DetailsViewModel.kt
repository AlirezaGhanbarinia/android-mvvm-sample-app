package com.aliasadi.mvvm.ui.details

import androidx.lifecycle.MutableLiveData
import com.aliasadi.mvvm.data.network.model.Movie
import com.aliasadi.mvvm.ui.base.BaseViewModel

class DetailsViewModel(private val movie: Movie) : BaseViewModel() {
    val movieLiveData = MutableLiveData<Movie>()
    fun loadMovieData() {
        movieLiveData.postValue(movie)
    }
}