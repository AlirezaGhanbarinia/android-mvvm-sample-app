package com.aliasadi.mvvm.ui.details;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aliasadi.mvvm.data.network.model.Movie;
import com.aliasadi.mvvm.ui.base.BaseViewModel;

class DetailsViewModel extends BaseViewModel {

    private final MutableLiveData<Movie> movieLiveData = new MutableLiveData<>();
    private final Movie movie;

    DetailsViewModel(Movie movie) {
        this.movie = movie;
    }

    void loadMovieData() {
        movieLiveData.postValue(movie);
    }

    MutableLiveData<Movie> getMovie() {
        return movieLiveData;
    }
}
