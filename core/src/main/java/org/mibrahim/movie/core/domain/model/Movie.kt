package org.mibrahim.movie.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val movieId: Int,
    val title: String,
    val backgroundPath: String,
    val overview: String,
    val rating: Double,
    val isFavorite: Boolean
) : Parcelable