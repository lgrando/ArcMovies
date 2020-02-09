package com.arctouch.codechallenge.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.repositories.MovieRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    var moviesPaged: LiveData<PagedList<Movie>>? = null

    init {
        viewModelScope.launch {
            moviesPaged = repository.getUpcomingMovies()
        }
    }

}