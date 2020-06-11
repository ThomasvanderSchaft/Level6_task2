package com.example.popularmovies.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieApi {
    companion object {
        private const val baseUrl = "https://api.themoviedb.org"

        fun createApi(): MovieApiService {
            // Create a Retrofit value
            val moviesApi = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            // Return API service
            return moviesApi.create(MovieApiService::class.java)
        }
    }
}