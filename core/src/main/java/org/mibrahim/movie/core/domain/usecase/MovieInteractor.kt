package org.mibrahim.movie.core.domain.usecase

import org.mibrahim.movie.core.domain.model.Movie
import org.mibrahim.movie.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {
    override fun getAllMovie() =movieRepository.getAllMovie()

    override fun getFavoriteMovie() = movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) =movieRepository.setFavoriteMovie(movie,state)


}