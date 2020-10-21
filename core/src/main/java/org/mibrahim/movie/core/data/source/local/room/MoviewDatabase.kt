package org.mibrahim.movie.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

import org.mibrahim.movie.core.data.source.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 5, exportSchema = false)
abstract class MoviewDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}