package com.aliasadi.mvvm.ui.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.aliasadi.mvvm.R;
import com.aliasadi.mvvm.data.network.model.Movie;
import com.aliasadi.mvvm.databinding.ActivityDetailsBinding;
import com.aliasadi.mvvm.ui.base.BaseActivity;
import com.bumptech.glide.Glide;

/**
 * Created by Ali Asadi on 12/03/2018.
 */
public class DetailsActivity extends BaseActivity<DetailsViewModel> {

    private static final String EXTRA_MOVIE = "EXTRA_MOVIE";

    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        viewModel.loadMovieData();
        viewModel.getMovie().observe(this, new MovieObserver());
    }

    @NonNull
    @Override
    protected DetailsViewModel createViewModel() {
        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        DetailsViewModelFactory factory = new DetailsViewModelFactory(movie);
        return new ViewModelProvider(this, factory).get(DetailsViewModel.class);
    }

    public static void start(Context context, Movie movie) {
        Intent starter = new Intent(context, DetailsActivity.class);
        starter.putExtra(EXTRA_MOVIE, movie);
        context.startActivity(starter);
    }

    private class MovieObserver implements Observer<Movie> {
        @Override
        public void onChanged(@Nullable Movie movie) {
            if (movie == null) return;

            binding.title.setText(movie.getTitle());
            binding.desc.setText(movie.getDescription());
            Glide.with(getApplicationContext()).load(movie.getImage()).into(binding.image);
        }
    }

}

