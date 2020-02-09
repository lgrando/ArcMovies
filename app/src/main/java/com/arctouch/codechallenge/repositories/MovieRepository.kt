package com.arctouch.codechallenge.repositories

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.arctouch.codechallenge.datasources.MovieDataSource
import com.arctouch.codechallenge.datasources.MoviePageDataSourceFactory
import com.arctouch.codechallenge.model.Movie

class MovieRepository(
    private val dataSource: MovieDataSource,
    private val pageFactory: MoviePageDataSourceFactory,
    private val pageConfig: PagedList.Config
) {

    suspend fun getUpcomingMovies() = dataSource.getUpcomingMovies()

    suspend fun getUpcomingMovies2(): LiveData<PagedList<Movie>> =
        LivePagedListBuilder<Long, Movie>(pageFactory, pageConfig).build()

    suspend fun getMovieDetails(id: Long) = dataSource.getMovieDetails(id)
}