package com.arctouch.codechallenge.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.repositories.MovieRepository
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MovieDetailViewModel(
    private val repository: MovieRepository,
    private val coroutineContext: CoroutineContext
) : ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _movie

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getMovieDetails(id: Long) {
        viewModelScope.launch(coroutineContext) {
            _isLoading.postValue(true)
            try {
                val response = repository.getMovieDetails(id)
                _movie.postValue(response)
            } catch (e: Exception) {
                _error.postValue(e.message)
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}