package com.arctouch.codechallenge.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.arctouch.codechallenge.CoroutineTestRule
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.repositories.MovieRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import kotlin.coroutines.CoroutineContext
import org.junit.Assert.assertEquals
import java.lang.Exception

class MovieDetailViewModelTest {

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var repositoryMock: MovieRepository
    private lateinit var coroutineContextMock: CoroutineContext

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        repositoryMock = mock()
        coroutineContextMock = coroutineTestRule.testDispatcher
        viewModel = MovieDetailViewModel(repositoryMock, coroutineContextMock)
    }

    @Test
    fun `Request movie details, then assert that livedata is filled when succeed`() {

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
            runBlocking(coroutineContextMock) {
                repositoryMock.getMovieDetails(any())
            }
        ).thenReturn(expectedResult)

        // Act
        viewModel.getMovieDetails(any())

        // Assert
        assertEquals(expectedResult, viewModel.movie.value)
    }

    @Test
    fun `Request movie details, then assert that livedata is filled when fail`() {

        // Arrange
        val expectedResult = "Error description"

        whenever(
            runBlocking(coroutineContextMock) {
                repositoryMock.getMovieDetails(any())
            }
        ).then {
            throw Exception(expectedResult)
        }

        // Act
        viewModel.getMovieDetails(any())

        // Assert
        assertEquals(expectedResult, viewModel.error.value)
    }
}