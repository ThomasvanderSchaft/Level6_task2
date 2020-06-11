package com.example.popularmovies.model

import com.google.gson.annotations.SerializedName

// Create list of movies that were returned from response
data class MovieResponse(
    @SerializedName("results") var movies: List<Movie>
)