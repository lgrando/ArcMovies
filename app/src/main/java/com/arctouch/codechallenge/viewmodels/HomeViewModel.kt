package com.arctouch.codechallenge.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.repositories.MovieRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import kotlin.coroutines.CoroutineContext

class HomeViewModel(
    private val repository: MovieRepository,
    private val coroutineContext: CoroutineContext
) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getUpcomingMovies() {
        viewModelScope.launch(coroutineContext) {
            try {
                val response = repository.getUpcomingMovies()
                _movies.postValue(response.results)
            } catch (e: Exception) {
                _error.postValue(e.message)
            }
        }
    }

}