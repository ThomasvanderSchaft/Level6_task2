package com.example.popularmovies.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.popularmovies.R
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private val movies: List<Movie>,
                   private val onClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )
    }

    // Get Int of movie items
    override fun getItemCount(): Int {
        return movies.size
    }

    // Bind movies to ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position], position+1)
    }

    // initialize inner class functions
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        init {
            // Handle an OnClickListener as soon as an item has been clicked on
            itemView.setOnClickListener {
                onClick(movies[adapterPosition])
            }
        }

        //  Bind movie id and poster URI to item_movie TextView and ImageView
        fun bind(movie: Movie, number: Int) {
            itemView.tvNumber.text = "$number."
            Glide.with(context).load(movie.getPosterUrl()).into(itemView.ivPoster)
        }
    }

}