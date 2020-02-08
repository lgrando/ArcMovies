package com.arctouch.codechallenge.views.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.util.EXTRA_MOVIE_ID
import com.arctouch.codechallenge.viewmodels.HomeViewModel
import com.arctouch.codechallenge.views.adapters.GenericAdapter
import com.arctouch.codechallenge.views.components.RequestFailureDialog
import kotlinx.android.synthetic.main.home_activity.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        configureObservers()
        viewModel.getUpcomingMovies()
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
                configureRequestFailureDialog()
            })
        }
    }

    private fun configureRequestFailureDialog() {
        RequestFailureDialog(
            retry = {
                viewModel.getUpcomingMovies()
            },
            cancel = {
                finish()
            }
        ).show(supportFragmentManager, RequestFailureDialog.REQUEST_FAILURE_DIALOG_TAG)
    }
}
