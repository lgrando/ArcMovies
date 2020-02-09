package com.arctouch.codechallenge.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.arctouch.codechallenge.datasources.GenreDataSource
import com.arctouch.codechallenge.model.Genre
import com.arctouch.codechallenge.model.GenreResponse
import com.arctouch.codechallenge.model.Movie
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class GenreRepositoryTest {

    private lateinit var repository: GenreRepository
    private lateinit var dataSourceMock: GenreDataSource

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        dataSourceMock = mock()
        repository = GenreRepository(dataSourceMock)
    }

    @Test
    fun `Get genres list, then assert suspend function returns`() {

        // Arrange
        val expectedResult = GenreResponse(
            listOf(Genre(1, "name"))
        )

        whenever(
            runBlocking {
                dataSourceMock.getGenres()
            }
        ).thenReturn(expectedResult)

        // Act
        runBlocking {
            val response = repository.getGenres()
            Assert.assertEquals(expectedResult, response)
        }
    }
}