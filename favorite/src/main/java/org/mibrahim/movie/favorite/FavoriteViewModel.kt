package org.mibrahim.movie.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import org.mibrahim.movie.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovie().asLiveData()
}

