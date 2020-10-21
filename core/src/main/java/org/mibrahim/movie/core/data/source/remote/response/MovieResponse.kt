package org.mibrahim.movie.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @field:SerializedName("id")
    val movieId: Int,

    @field:SerializedName("original_title")
    val title: String,

    @field:SerializedName("backdrop_path")
    val backdropPath: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("vote_average")
    val rating: Double
)

