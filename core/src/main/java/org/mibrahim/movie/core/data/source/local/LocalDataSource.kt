package org.mibrahim.movie.core.data.source.local

import kotlinx.coroutines.flow.Flow
import org.mibrahim.movie.core.data.source.local.entity.MovieEntity
import org.mibrahim.movie.core.data.source.local.room.MovieDao

class LocalDataSource(private val movieDao: MovieDao) {

    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovie()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()

    suspend fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
}