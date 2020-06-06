package com.example.popularmovies.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.popularmovies.R
import com.example.popularmovies.model.Movie
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initViews()
    }

    private fun initViews() {
        movie = intent.extras?.getParcelable(EXTRA_MOVIE)!!

        tvTitle.text = movie.title
        Glide.with(this).load(movie.getPosterUrl()).into(ivPoster)
        Glide.with(this).load(movie.getBackdropUrl()).into(ivBackdrop)
        tvRating.text = movie.rating.toString()
        tvReleaseDate.text = "Release: ${movie.releaseDate}"
        tvOverview.text = movie.overview

        var isShow = true
        var scrollRange = -1
        appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1){
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0){
                collapsingToolbarLayout.title = movie.title
                collapsingToolbarLayout.setCollapsedTitleTextColor(Color.parseColor("#FFFFFFFF"))
                isShow = true
            } else if (isShow){
                collapsingToolbarLayout.title = " " //careful there should a space between double quote otherwise it wont work
                isShow = false
            }
        })

    }
}