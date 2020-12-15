package com.aliasadi.mvvm.ui.main

import androidx.lifecycle.MutableLiveData
import com.aliasadi.mvvm.data.network.model.Movie
import com.aliasadi.mvvm.data.network.model.MovieResponse
import com.aliasadi.mvvm.data.network.services.MovieService
import com.aliasadi.mvvm.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainViewModel(private val movieService: MovieService) : BaseViewModel() {

    val movies: MutableLiveData<List<Movie>> = MutableLiveData()
    val loadingStatus: MutableLiveData<Boolean> = MutableLiveData()

    fun loadMoviesNetwork() {
        setIsLoading(true)
        movieService.movieApi.allMovie.enqueue(MovieCallback())
    }

    fun loadMovieLocal() {
        setIsLoading(true)
        setMovies(createLocalMovieList())
    }

    fun onEmptyClicked() {
        setMovies(emptyList())
    }

    private fun createLocalMovieList(): List<Movie> {
        val name = "Breaking Bad"
        val image = "https://coderwall-assets-0.s3.amazonaws.com/" +
                "uploads/picture/file/622/breaking_bad_css3_svg_raw.png"
        val movies: MutableList<Movie> = ArrayList()
        movies.add(Movie(name, image, name))
        movies.add(Movie(name, image, name))
        movies.add(Movie(name, image, name))
        return movies
    }

    private fun setIsLoading(loading: Boolean) {
        loadingStatus.postValue(loading)
    }

    private fun setMovies(movies: List<Movie>?) {
        setIsLoading(false)
        this.movies.postValue(movies)
    }

    private inner class MovieCallback : Callback<MovieResponse?> {
        override fun onResponse(call: Call<MovieResponse?>, response: Response<MovieResponse?>) {
            val movieResponse = response.body()
            if (movieResponse != null) {
                setMovies(movieResponse.movies)
            } else {
                setMovies(emptyList())
            }
        }

        override fun onFailure(call: Call<MovieResponse?>, t: Throwable) {
            setMovies(emptyList())
        }
    }

}