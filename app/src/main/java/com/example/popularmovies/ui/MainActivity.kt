package com.example.popularmovies.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovies.R
import com.example.popularmovies.model.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.floor

const val EXTRA_MOVIE = "EXTRA_MOVIE"

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private val movies = arrayListOf<Movie>()
    private val movieAdapter = MovieAdapter(movies) {movieItem -> onMovieClick(movieItem) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        val gridLayoutManager = GridLayoutManager(this, 1, RecyclerView.VERTICAL, false)
        rvMovies.layoutManager = gridLayoutManager

        // Add Global Layout Listener to calculate the span count.
        rvMovies.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                rvMovies.viewTreeObserver.removeOnGlobalLayoutListener(this)
                gridLayoutManager.spanCount = calculateSpanCount()
                gridLayoutManager.requestLayout()
            }
        })

        rvMovies.adapter = movieAdapter
        btnSubmit.setOnClickListener {
            viewModel.getMovies(etYear.text.toString())
        }
    }

    // Initialize ViewModel of MainActivity
    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.moviesPage.observe(this, Observer {
            movies.clear()
            movies.addAll(it.movies)
            movieAdapter.notifyDataSetChanged()
        })
        // Handle error message
        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
        // Handle progressBar visibility
        viewModel.progressBarStatus.observe(this, Observer {
            if (it) progressBar.visibility = View.VISIBLE
            else progressBar.visibility = View.GONE
        })
    }

    // Create intent from clicked movie
    private fun onMovieClick(movieItem: Movie) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(EXTRA_MOVIE, movieItem)
        startActivityForResult(intent, 100)
    }

    // Calculate span count of RecyclerView
    private fun calculateSpanCount(): Int {
        val viewWidth = rvMovies.measuredWidth
        val cardViewWidth = resources.getDimension(R.dimen.cardWidth)
        val cardViewMargin = resources.getDimension(R.dimen.cardMargin)
        val spanCount = floor((viewWidth / (cardViewWidth + cardViewMargin)).toDouble()).toInt()
        return if (spanCount >= 1) spanCount else 1
    }

}