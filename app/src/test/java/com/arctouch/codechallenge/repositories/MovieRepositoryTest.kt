package com.arctouch.codechallenge.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagedList
import com.arctouch.codechallenge.datasources.MovieDataSource
import com.arctouch.codechallenge.datasources.MoviePageDataSourceFactory
import com.arctouch.codechallenge.model.Movie
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.Assert.assertEquals

class MovieRepositoryTest {

    private lateinit var repository: MovieRepository
    private lateinit var movieDataSourceMock: MovieDataSource
    private lateinit var moviePageDataSourceFactoryMock: MoviePageDataSourceFactory
    private lateinit var pageConfigMock: PagedList.Config

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        movieDataSourceMock = mock()
        moviePageDataSourceFactoryMock = mock()
        pageConfigMock = mock()
        repository = MovieRepository(
            movieDataSourceMock,
            moviePageDataSourceFactoryMock,
            pageConfigMock
        )
    }

    @Test
    fun `Get movie details by ID, then assert suspend function returns`() {

        // Arrange
        val expectedResult = Movie(
            id = 1,
            title = "title",
            overview = "overview",
            genres = listOf(),
            genreIds = listOf(),
            posterPath = "posterPath",
            backdropPath = "backdropPath",
            releaseDate = "releaseDate"
        )

        whenever(
            runBlocking {
                movieDataSourceMock.getMovieDetails(any())
            }
        ).thenReturn(expectedResult)

        // Act
        runBlocking {
            val response = repository.getMovieDetails(any())
            assertEquals(expectedResult, response)
        }
    }
}