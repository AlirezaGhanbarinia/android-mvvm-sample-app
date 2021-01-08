package com.aliasadi.mvvm.data.network.services

import com.aliasadi.mvvm.data.network.model.MovieResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MovieService {
    val movieApi: MovieApi

    interface MovieApi {
        @get:GET("movies/")
        val allMovie: Call<MovieResponse?>
    }

    companion object {

        private const val URL = "http://demo6483760.mockable.io/"

        val instance: MovieService by lazy {
            MovieService()
        }
    }

    init {
        val mRetrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(URL).build()
        movieApi = mRetrofit.create(MovieApi::class.java)
    }
}