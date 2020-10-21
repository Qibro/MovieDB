package org.mibrahim.movie.detail

import androidx.lifecycle.ViewModel
import org.mibrahim.movie.core.domain.model.Movie
import org.mibrahim.movie.core.domain.usecase.MovieUseCase

class DetailMovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newStatus:Boolean) = movieUseCase.setFavoriteMovie(movie, newStatus)
}

