package com.arctouch.codechallenge.views.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.databinding.ActivityMovieDetailBinding
import com.arctouch.codechallenge.model.Genre
import com.arctouch.codechallenge.util.EXTRA_MOVIE_ID
import com.arctouch.codechallenge.viewmodels.MovieDetailViewModel
import com.arctouch.codechallenge.views.adapters.GenericAdapter
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailActivity : AppCompatActivity() {

    private val viewModel: MovieDetailViewModel by viewModel()

    private lateinit var binding: ActivityMovieDetailBinding
    private var movieId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)

        configureObservers()

        movieId = intent.getLongExtra(EXTRA_MOVIE_ID, 0)
        viewModel.getMovieDetails(movieId)
    }

    private fun configureObservers() {
        viewModel.apply {
            movie.observe(this@MovieDetailActivity, Observer { movie ->
                binding.movie = movie
                configureGenres(movie.genres)
            })

            error.observe(this@MovieDetailActivity, Observer {

            })

            isLoading.observe(this@MovieDetailActivity, Observer {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
            })
        }
    }

    private fun configureGenres(genres: List<Genre>?) {
        genres?.let { list ->
            val adapter = GenericAdapter<Genre>(R.layout.genre_item)

            rvGenres.adapter = adapter
            adapter.setupItems(list)
        }
    }
}
