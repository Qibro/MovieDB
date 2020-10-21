package org.mibrahim.movie.favorite.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { org.mibrahim.movie.favorite.FavoriteViewModel(get()) }
}
