package com.arctouch.codechallenge.repositories

import com.arctouch.codechallenge.datasources.MovieDataSource

class MovieRepository(private val dataSource: MovieDataSource) {

    suspend fun getUpcomingMovies() = dataSource.getUpcomingMovies()

    suspend fun getMovieDetails(id: Long) = dataSource.getMovieDetails(id)
}