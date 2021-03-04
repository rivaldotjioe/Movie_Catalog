package com.rivaldo.moviecatalog.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rivaldo.moviecatalog.database.Movie
import androidx.paging.DataSource
import com.rivaldo.moviecatalog.database.Tv

@Dao
interface CatalogDao {

    @Query("SELECT * FROM favoritemovie")
    fun getFavoriteMovie(): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM favoritetv")
    fun getFavoriteTv(): DataSource.Factory<Int, Tv>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteTv(favoritetv: Tv)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteMovie(favoritemovie: Movie)

    @Query("SELECT EXISTS (SELECT 1 FROM favoritemovie where movieId = :id)")
    fun checkFavoriteMovie(id: Int) : Boolean

    @Query("SELECT EXISTS (SELECT 1 FROM favoritetv where tvid = :id)")
    fun checkFavoriteTv(id: Int) : Boolean

    @Delete
    fun deleteFavoriteMovie(movie: Movie)

    @Delete
    fun deleteFavoriteTv(tv: Tv)


}