package com.aliasadi.mvvm.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieResponse {
    @Expose
    @SerializedName("movies")
    val movies: List<Movie>? = null
}