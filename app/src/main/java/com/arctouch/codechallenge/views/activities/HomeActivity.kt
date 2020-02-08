package com.arctouch.codechallenge.views.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.viewmodels.HomeViewModel
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.util.EXTRA_MOVIE_ID
import com.arctouch.codechallenge.views.adapters.GenericAdapter
import kotlinx.android.synthetic.main.home_activity.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        configureObservers()
        viewModel.getUpcomingMovies()

//        api.upcomingMovies(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE, 1, TmdbApi.DEFAULT_REGION)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                val moviesWithGenres = it.results.map { movie ->
//                    movie.copy(genres = Cache.genres.filter { movie.genreIds?.contains(it.id) == true })
//                }
//                recyclerView.adapter = HomeAdapter(moviesWithGenres)
//                progressBar.visibility = View.GONE
//            }
    }

    private fun configureObservers() {
        viewModel.apply {
            movies.observe(this@HomeActivity, Observer {
                val adapter = GenericAdapter<Movie>(R.layout.movie_item) { movie ->
                    val intent = Intent(this@HomeActivity, MovieDetailActivity::class.java)
                    intent.putExtra(EXTRA_MOVIE_ID, movie.id)
                    startActivity(intent)
                }

                recyclerView.adapter = adapter
                adapter.setupItems(it)
            })
            error.observe(this@HomeActivity, Observer {
                // TODO: Error layout with retry
                Toast.makeText(this@HomeActivity, "Error", Toast.LENGTH_SHORT).show()
            })
        }
    }
}
