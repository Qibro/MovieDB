package org.mibrahim.movie.core.data.source.remote.network

import org.mibrahim.movie.core.data.source.remote.response.ListMovieResponse
import org.mibrahim.movie.core.utils.Constants.Companion.API_KEY
import retrofit2.http.GET

interface ApiService {
    @GET("discover/movie?api_key=$API_KEY")
    suspend fun getMovies(): ListMovieResponse
}