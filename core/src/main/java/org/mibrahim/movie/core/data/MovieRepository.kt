package org.mibrahim.movie.core.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mibrahim.movie.core.data.source.remote.network.ApiResponse
import org.mibrahim.movie.core.data.source.local.LocalDataSource
import org.mibrahim.movie.core.data.source.remote.RemoteDataSource
import org.mibrahim.movie.core.data.source.remote.response.MovieResponse
import org.mibrahim.movie.core.domain.model.Movie
import org.mibrahim.movie.core.domain.repository.IMovieRepository
import org.mibrahim.movie.core.utils.AppExecutors
import org.mibrahim.movie.core.utils.DataMapper

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = data == null || data.isEmpty()


            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovie()


            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }

        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }


}