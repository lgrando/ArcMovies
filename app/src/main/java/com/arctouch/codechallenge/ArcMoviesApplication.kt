package com.arctouch.codechallenge

import android.app.Application
import org.koin.core.context.startKoin

class ArcMoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
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
}