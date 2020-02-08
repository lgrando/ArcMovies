package com.arctouch.codechallenge.extensions

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.arctouch.codechallenge.model.Genre

@BindingAdapter("genresLabel")
fun TextView.setGenresLabel(genres: List<Genre>?) {
    genres?.let { list ->
        this.text = list.joinToString(", ") { it.name }
    }
}