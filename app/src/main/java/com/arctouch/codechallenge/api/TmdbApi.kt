package com.arctouch.codechallenge.api

import com.arctouch.codechallenge.model.GenreResponse
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.model.UpcomingMoviesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("genre/movie/list")
    fun genres(): Observable<GenreResponse>

    @GET("movie/upcoming")
    suspend fun upcomingMovies(
        @Query("page") page: Long
    ): UpcomingMoviesResponse

    @GET("movie/{id}")
    suspend fun movie(
        @Path("id") id: Long
    ): Movie
}
