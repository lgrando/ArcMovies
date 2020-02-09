package com.arctouch.codechallenge.repositories

import com.arctouch.codechallenge.datasources.GenreDataSource

class GenreRepository(private val dataSource: GenreDataSource) {

    suspend fun getGenres() = dataSource.getGenres()
}