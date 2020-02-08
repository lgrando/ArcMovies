package com.arctouch.codechallenge.datasources

import com.arctouch.codechallenge.api.TmdbApi

class MovieRemoteDataSource(private val service: TmdbApi) {

    suspend fun getUpcomingMovies() = service.upcomingMovies(1)

    suspend fun getMovieDetails(id: Long) = service.movie(id)
}