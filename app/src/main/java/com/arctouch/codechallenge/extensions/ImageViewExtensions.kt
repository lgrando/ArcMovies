package com.arctouch.codechallenge.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.util.BACKDROP_URL
import com.arctouch.codechallenge.util.POSTER_URL
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("moviePosterSrc")
fun ImageView.setMoviePosterSrc(url: String?) {
    url?.let {
        Glide.with(this)
            .load("$POSTER_URL$url")
            .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
            .into(this)
    }
}

@BindingAdapter("movieBackdropSrc")
fun ImageView.setMovieBackdropSrc(url: String?) {
    url?.let {
        Glide.with(this)
            .load("$BACKDROP_URL$url")
            .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
            .into(this)
    }
}