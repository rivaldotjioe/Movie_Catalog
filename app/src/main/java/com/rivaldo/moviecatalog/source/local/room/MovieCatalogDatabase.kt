package com.rivaldo.moviecatalog.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rivaldo.moviecatalog.database.Movie
import com.rivaldo.moviecatalog.database.Tv

@Database(entities = [Movie::class, Tv::class],
    version = 1,
    exportSchema = false
)
abstract class MovieCatalogDatabase : RoomDatabase() {
    abstract fun movieCatalogDao(): CatalogDao

    companion object {
        @Volatile
        private var INSTANCE: MovieCatalogDatabase? = null

        fun getInstance(context: Context): MovieCatalogDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(context.applicationContext,
                MovieCatalogDatabase::class.java,
                "Movie.db").build()
            }
    }
}