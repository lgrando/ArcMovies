package com.arctouch.codechallenge.datasources

import com.arctouch.codechallenge.api.TmdbApi

class MovieDataSource(private val service: TmdbApi) {

    suspend fun getUpcomingMovies() = service.upcomingMovies(1)

    suspend fun getMovieDetails(id: Long) = service.movie(id)
}