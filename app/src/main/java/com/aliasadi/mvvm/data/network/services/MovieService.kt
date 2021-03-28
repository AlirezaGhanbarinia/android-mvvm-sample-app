package com.aliasadi.mvvm.data.network.services

import com.aliasadi.mvvm.data.network.model.MovieResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MovieService {

    private val url = "http://demo6483760.mockable.io/"

    val movieApi: MovieApi by lazy {
        Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build()
                .create(MovieApi::class.java)
    }

    interface MovieApi {
        @get:GET("movies/")
        val allMovie: Call<MovieResponse?>
    }

    companion object {
        val instance: MovieService by lazy {
            MovieService()
        }
    }
}