package com.arctouch.codechallenge.datasources

import androidx.paging.DataSource
import com.arctouch.codechallenge.api.TmdbApi
import com.arctouch.codechallenge.model.Movie
import kotlinx.coroutines.CoroutineScope

class MoviePageDataSourceFactory(
    private val service: TmdbApi,
    private val coroutineScope: CoroutineScope
) : DataSource.Factory<Long, Movie>() {

    override fun create(): DataSource<Long, Movie> = MoviePageDataSource(service, coroutineScope)
}