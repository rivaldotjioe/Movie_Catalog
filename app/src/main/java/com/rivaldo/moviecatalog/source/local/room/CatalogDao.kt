package com.rivaldo.moviecatalog.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rivaldo.moviecatalog.database.Movie

@Dao
interface CatalogDao {

    @Query("SELECT * FROM favoritemovie")
    fun getFavoriteMovie(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteMovie(favoritemovie: Movie)

    @Query("SELECT EXISTS (SELECT 1 FROM favoritemovie where movieId = :id)")
    fun checkFavoriteMovie(id: Int) : Boolean

    @Delete
    fun deleteFavoriteMovie(movie: Movie)


}