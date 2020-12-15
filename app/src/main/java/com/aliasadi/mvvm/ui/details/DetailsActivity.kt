package com.aliasadi.mvvm.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aliasadi.mvvm.R
import com.aliasadi.mvvm.data.network.model.Movie
import com.aliasadi.mvvm.ui.base.BaseActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : BaseActivity<DetailsViewModel>() {

    companion object {
        private const val EXTRA_MOVIE = "EXTRA_MOVIE"
        fun start(context: Context, movie: Movie) {
            val starter = Intent(context, DetailsActivity::class.java).apply {
                putExtra(EXTRA_MOVIE, movie)
            }
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        viewModel.loadMovieData()
        viewModel.movieLiveData.observe(this, MovieObserver())
    }

    override fun createViewModel(): DetailsViewModel {
        val movie: Movie = intent.getParcelableExtra(EXTRA_MOVIE)!!
        val factory = DetailsViewModelFactory(movie)
        return ViewModelProvider(this, factory).get(DetailsViewModel::class.java)
    }

    private inner class MovieObserver : Observer<Movie?> {
        override fun onChanged(movie: Movie?) {
            if (movie == null) return
            tvTitle.text = movie.title
            tvDesc.text = movie.description
            Glide.with(applicationContext).load(movie.image).into(image)
        }
    }
}