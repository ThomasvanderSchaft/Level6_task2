package com.example.popularmovies.api
import com.example.popularmovies.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

public interface MovieApiService {
    @GET("/3/discover/movie")
    fun getMoviesForYear(@QueryMap params: Map<String, String>): Call<MovieResponse>
}