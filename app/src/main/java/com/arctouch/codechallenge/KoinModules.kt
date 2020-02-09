package com.arctouch.codechallenge

import androidx.paging.PagedList
import com.arctouch.codechallenge.api.TmdbApi
import com.arctouch.codechallenge.datasources.GenreDataSource
import com.arctouch.codechallenge.datasources.MovieDataSource
import com.arctouch.codechallenge.datasources.MoviePageDataSourceFactory
import com.arctouch.codechallenge.repositories.GenreRepository
import com.arctouch.codechallenge.repositories.MovieRepository
import com.arctouch.codechallenge.util.buildApi
import com.arctouch.codechallenge.viewmodels.HomeViewModel
import com.arctouch.codechallenge.viewmodels.MovieDetailViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val serviceModule = module {
    factory { buildApi(TmdbApi::class.java) }
}

val dataSourceModule = module {
    factory {
        PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()
    }
    factory { MovieDataSource(get()) }
    factory { GenreDataSource(get()) }
    factory { MoviePageDataSourceFactory(get(), CoroutineScope(Dispatchers.IO + Job())) }
}

val repositoryModule = module {
    factory { MovieRepository(get(), get(), get()) }
    factory { GenreRepository(get()) }
}

val viewModelModule = module {
    viewModel {
        HomeViewModel(get())
    }
    viewModel {
        MovieDetailViewModel(get(), Dispatchers.IO)
    }
}