package com.arctouch.codechallenge.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arctouch.codechallenge.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

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
}
