package org.mibrahim.movie.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.mibrahim.movie.core.domain.usecase.MovieInteractor
import org.mibrahim.movie.core.domain.usecase.MovieUseCase
import org.mibrahim.movie.detail.DetailMovieViewModel
import org.mibrahim.movie.home.HomeViewModel

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}
val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
}
