package com.example.popularmovies.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results") var movies: List<Movie>
)