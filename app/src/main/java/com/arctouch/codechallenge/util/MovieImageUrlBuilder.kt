package com.arctouch.codechallenge.util

class MovieImageUrlBuilder {

    fun buildPosterUrl(posterPath: String) = "$POSTER_URL$posterPath?api_key=$API_KEY"
    fun buildBackdropUrl(backdropPath: String) = "$BACKDROP_URL$backdropPath?api_key=$API_KEY"
}
