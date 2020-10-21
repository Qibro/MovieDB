package org.mibrahim.movie.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.mibrahim.movie.core.data.Resource
import org.mibrahim.movie.core.domain.model.Movie

interface MovieUseCase {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}