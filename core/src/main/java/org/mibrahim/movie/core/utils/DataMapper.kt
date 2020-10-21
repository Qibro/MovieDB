package org.mibrahim.movie.core.utils

import org.mibrahim.movie.core.data.source.local.entity.MovieEntity
import org.mibrahim.movie.core.data.source.remote.response.MovieResponse
import org.mibrahim.movie.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.movieId,
                title = it.title,
                backdropPath = it.backdropPath,
                overview = it.overview,
                rating = it.rating,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                title = it.title,
                backgroundPath = it.backdropPath,
                overview = it.overview,
                rating = it.rating,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        movieId = input.movieId,
        title = input.title,
        backdropPath = input.backgroundPath,
        overview = input.overview,
        rating = input.rating,
        isFavorite = input.isFavorite
    )

}