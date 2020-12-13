package com.aliasadi.mvvm.ui.details;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.aliasadi.mvvm.data.network.model.Movie;

public class DetailsViewModelFactory implements ViewModelProvider.Factory {

    private final Movie movie;

    public DetailsViewModelFactory(Movie movie) {
        this.movie = movie;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DetailsViewModel.class)) {
            return (T) new DetailsViewModel(movie);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
