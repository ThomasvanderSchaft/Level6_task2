package com.example.popularmovies.repo

import android.content.Context
import com.example.popularmovies.R
import com.example.popularmovies.api.*

class MoviesRepository(var context: Context) {
    private val moviesApi: MovieApiService = MovieApi.createApi()

    // Get movie information form API with criteria
    fun getMovies(year: String) = moviesApi.getMoviesForYear(
        mapOf(
            "api_key" to context.getString(R.string.key),
            "language" to "en-US",
            "sort_by" to "popularity.desc",
            "include_adult" to "false",
            "include_video" to "false",
            "page" to "1",
            "primary_release_year" to year)
    )
}