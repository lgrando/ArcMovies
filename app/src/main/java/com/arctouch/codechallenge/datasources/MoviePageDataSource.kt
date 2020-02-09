package com.arctouch.codechallenge.datasources

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.arctouch.codechallenge.api.TmdbApi
import com.arctouch.codechallenge.data.Cache
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.model.UpcomingMoviesResponse
import com.arctouch.codechallenge.util.MOVIE_PAGING_ERROR_TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MoviePageDataSource(
    private val service: TmdbApi,
    private val coroutineScope: CoroutineScope
) : PageKeyedDataSource<Long, Movie>() {

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, Movie>
    ) {
        coroutineScope.launch {
            try {
                val response = service.upcomingMovies(1)
                val updatedMovies = getMoviesGenresFromCache(response)
                callback.onResult(updatedMovies, null, 2)
            } catch (e: Exception) {
                Log.e(MOVIE_PAGING_ERROR_TAG, "loadInital error: $e")
            }
        }
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Movie>) {
        coroutineScope.launch {
            try {
                val response = service.upcomingMovies(params.key)
                val updatedMovies = getMoviesGenresFromCache(response)
                callback.onResult(updatedMovies, params.key + 1)
            } catch (e: Exception) {
                Log.e(MOVIE_PAGING_ERROR_TAG, "loadAfter error: $e")
            }
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Movie>) {
        coroutineScope.launch {
            try {
                val response = service.upcomingMovies(0)
                val updatedMovies = getMoviesGenresFromCache(response)
                callback.onResult(updatedMovies, params.key - 1)
            } catch (e: Exception) {
                Log.e(MOVIE_PAGING_ERROR_TAG, "loadBefore error: $e")
            }
        }
    }

    private fun getMoviesGenresFromCache(response: UpcomingMoviesResponse): List<Movie> {
        return response.results.map { movie ->
            movie.copy(genres = Cache.genres.filter { movie.genreIds?.contains(it.id) == true })
        }
    }

}