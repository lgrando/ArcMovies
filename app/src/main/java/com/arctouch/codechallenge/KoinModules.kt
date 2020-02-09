package com.arctouch.codechallenge

import com.arctouch.codechallenge.api.TmdbApi
import com.arctouch.codechallenge.datasources.GenreDataSource
import com.arctouch.codechallenge.datasources.MovieDataSource
import com.arctouch.codechallenge.repositories.GenreRepository
import com.arctouch.codechallenge.repositories.MovieRepository
import com.arctouch.codechallenge.util.buildApi
import com.arctouch.codechallenge.viewmodels.HomeViewModel
import com.arctouch.codechallenge.viewmodels.MovieDetailViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val serviceModule = module {
    factory { buildApi(TmdbApi::class.java) }
}

val dataSourceModule = module {
    factory { MovieDataSource(get()) }
    factory { GenreDataSource(get()) }
}

val repositoryModule = module {
    factory { MovieRepository(get()) }
    factory { GenreRepository(get()) }
}

val viewModelModule = module {
    viewModel {
        HomeViewModel(get(), Dispatchers.IO)
    }
    viewModel {
        MovieDetailViewModel(get(), Dispatchers.IO)
    }
}