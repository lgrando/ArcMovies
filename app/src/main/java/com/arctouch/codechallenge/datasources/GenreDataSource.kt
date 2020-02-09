package com.arctouch.codechallenge.datasources

import com.arctouch.codechallenge.api.TmdbApi

class GenreDataSource(private val service: TmdbApi) {

    suspend fun getGenres() = service.genres()
}