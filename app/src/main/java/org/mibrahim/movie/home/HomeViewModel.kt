package org.mibrahim.movie.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import org.mibrahim.movie.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovie().asLiveData()
}

