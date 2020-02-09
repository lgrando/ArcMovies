package com.arctouch.codechallenge

import android.app.Application
import android.util.Log
import com.arctouch.codechallenge.data.Cache
import com.arctouch.codechallenge.repositories.GenreRepository
import com.arctouch.codechallenge.util.CACHE_GENRES_ERROR_TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.context.startKoin

class ArcMoviesApplication : Application() {

    private val repository: GenreRepository by inject()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + Job())

    override fun onCreate() {
        super.onCreate()
        setupKoin()
        configureGenres()
    }

    private fun setupKoin() {
        startKoin {
            modules(
                listOf(
                    serviceModule,
                    dataSourceModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }

    private fun configureGenres() {
        coroutineScope.launch {
            try {
                val response = repository.getGenres()
                Cache.cacheGenres(response.genres)
            } catch (e: Exception) {
                Log.e(CACHE_GENRES_ERROR_TAG, e.toString())
            }
        }
    }
}