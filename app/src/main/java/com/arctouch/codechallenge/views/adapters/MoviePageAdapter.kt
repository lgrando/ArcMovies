package com.arctouch.codechallenge.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.databinding.MovieItemBinding
import com.arctouch.codechallenge.model.Movie

class MoviePageAdapter(
    private val listener: (Movie) -> Unit
) : PagedListAdapter<Movie, MoviePageAdapter.ViewHolder>(callback) {

    class ViewHolder(
        private val binding: MovieItemBinding,
        private val listener: (Movie) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(movie: Movie?) {
            movie?.let {
                binding.item = it
                binding.executePendingBindings()
                itemView.setOnClickListener { listener(movie) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: MovieItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.movie_item,
            parent,
            false
        )

        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.onBind(getItem(position))
}

private val callback = object : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}