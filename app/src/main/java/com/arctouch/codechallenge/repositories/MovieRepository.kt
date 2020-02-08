package com.arctouch.codechallenge.repositories

import com.arctouch.codechallenge.datasources.MovieRemoteDataSource

class MovieRepository(private val dataSource: MovieRemoteDataSource) {

    suspend fun getUpcomingMovies() = dataSource.getUpcomingMovies()

    suspend fun getMovieDetails(id: Long) = dataSource.getMovieDetails(id)
}