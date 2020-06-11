package com.example.popularmovies.ui

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.popularmovies.model.MovieResponse
import com.example.popularmovies.repo.MoviesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val moviesRepository = MoviesRepository(application.applicationContext)
    val moviesPage = MutableLiveData<MovieResponse>()
    val error = MutableLiveData<String>()
    val progressBarStatus = MutableLiveData<Boolean>(false)

    // Initialize call to repo for all movies with the year criteria
    fun getMovies(year: String) {
        progressBarStatus.value = true
        moviesRepository.getMovies(year).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) moviesPage.value = response.body()
                else error.value = "An error occurred: ${response.errorBody().toString()}"
                progressBarStatus.value = false
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                error.value = t.message
                progressBarStatus.value = false
            }
        })
    }
}