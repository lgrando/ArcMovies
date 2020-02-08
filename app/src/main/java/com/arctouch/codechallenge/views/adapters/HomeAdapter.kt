package com.arctouch.codechallenge.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.model.Movie
import kotlinx.android.synthetic.main.movie_item.view.*

class HomeAdapter(private val movies: List<Movie>) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

//        private val movieImageUrlBuilder = MovieImageUrlBuilder()

        fun bind(movie: Movie) {
            itemView.titleTextView.text = movie.title
            itemView.genresTextView.text = movie.genres?.joinToString(separator = ", ") { it.name }
            itemView.releaseDateTextView.text = movie.releaseDate

//            Glide.with(itemView)
//                .load(movie.posterPath?.let { movieImageUrlBuilder.buildPosterUrl(it) })
//                .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
//                .into(itemView.posterImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(movies[position])
}
