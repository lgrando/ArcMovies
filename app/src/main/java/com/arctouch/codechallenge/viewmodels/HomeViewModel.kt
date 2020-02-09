package com.arctouch.codechallenge.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.repositories.MovieRepository
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeViewModel(
    private val repository: MovieRepository,
    private val coroutineContext: CoroutineContext
) : ViewModel() {

    var movies: LiveData<PagedList<Movie>>? = null

    init {
        viewModelScope.launch(coroutineContext) {
            movies = repository.getUpcomingMovies2()
        }
    }

}