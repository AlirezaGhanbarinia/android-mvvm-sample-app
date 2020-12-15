package com.aliasadi.mvvm.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aliasadi.mvvm.R
import com.aliasadi.mvvm.data.DataManager
import com.aliasadi.mvvm.data.network.model.Movie
import com.aliasadi.mvvm.ui.base.BaseActivity
import com.aliasadi.mvvm.ui.details.DetailsActivity
import com.aliasadi.mvvm.ui.main.MovieAdapter.MovieListener
import kotlinx.android.synthetic.main.activity_main.*

 class MainActivity : BaseActivity<MainViewModel>(), MovieListener {

    private var movieAdapter: MovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieAdapter = MovieAdapter(this)
        recyclerView.adapter = movieAdapter

        viewModel.loadingStatus.observe(this, LoadingObserver())
        viewModel.movies.observe(this, MovieObserver())

        network.setOnClickListener { viewModel.loadMoviesNetwork() }
        local.setOnClickListener { viewModel.loadMovieLocal() }
        empty.setOnClickListener { viewModel.onEmptyClicked() }
    }

    override fun createViewModel(): MainViewModel {
        val factory = MainViewModelFactory(DataManager.getInstance().movieService())
        return ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    override fun onMovieClicked(movie: Movie) {
        DetailsActivity.start(this, movie)
    }

    private inner class LoadingObserver : Observer<Boolean?> {
        override fun onChanged(isLoading: Boolean?) {
            if (isLoading == null) return
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }

    private inner class MovieObserver : Observer<List<Movie>?> {
        override fun onChanged(movies: List<Movie>?) {
            if (movies == null) return
            movieAdapter!!.setItems(movies)
            if (movies.isEmpty()) {
                emptyView.visibility = View.VISIBLE
            } else {
                emptyView.visibility = View.GONE
            }
        }
    }
}