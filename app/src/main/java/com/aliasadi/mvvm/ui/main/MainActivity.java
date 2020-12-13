package com.aliasadi.mvvm.ui.main;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.aliasadi.mvvm.R;
import com.aliasadi.mvvm.data.DataManager;
import com.aliasadi.mvvm.data.network.model.Movie;
import com.aliasadi.mvvm.databinding.ActivityMainBinding;
import com.aliasadi.mvvm.ui.base.BaseActivity;
import com.aliasadi.mvvm.ui.details.DetailsActivity;

import java.util.List;

/**
 * Created by Ali Asadi on 12/03/2018.
 */

public class MainActivity extends BaseActivity<MainViewModel> implements MovieAdapter.MovieListener {

    private ActivityMainBinding binding;

    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        movieAdapter = new MovieAdapter(this);
        binding.recyclerView.setAdapter(movieAdapter);

        viewModel.getLoadingStatus().observe(this, new LoadingObserver());
        viewModel.getMovies().observe(this, new MovieObserver());

        binding.network.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNetworkButtonClick();
            }
        });

        binding.local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLocalButtonClick();
            }
        });

        binding.empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEmptyButtonClick();
            }
        });
    }

    @NonNull
    @Override
    protected MainViewModel createViewModel() {
        MainViewModelFactory factory = new MainViewModelFactory(DataManager.getInstance().getMovieService());
        return new ViewModelProvider(this, factory).get(MainViewModel.class);
    }

    void onNetworkButtonClick() {
        viewModel.loadMoviesNetwork();
    }

    void onLocalButtonClick() {
        viewModel.loadMovieLocal();
    }

    void onEmptyButtonClick() {
        viewModel.onEmptyClicked();
    }

    @Override
    public void onMovieClicked(Movie movie) {
        DetailsActivity.start(this, movie);
    }

    //Observer
    private class LoadingObserver implements Observer<Boolean> {

        @Override
        public void onChanged(@Nullable Boolean isLoading) {
            if (isLoading == null) return;

            if (isLoading) {
                binding.progressBar.setVisibility(View.VISIBLE);
            } else {
                binding.progressBar.setVisibility(View.GONE);
            }
        }
    }

    private class MovieObserver implements Observer<List<Movie>> {

        @Override
        public void onChanged(@Nullable List<Movie> movies) {
            if (movies == null) return;
            movieAdapter.setItems(movies);

            if (movies.isEmpty()) {
                binding.emptyView.setVisibility(View.VISIBLE);
            } else {
                binding.emptyView.setVisibility(View.GONE);
            }
        }
    }
}
