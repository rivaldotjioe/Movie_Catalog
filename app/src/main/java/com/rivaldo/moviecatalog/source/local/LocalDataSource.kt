package com.rivaldo.moviecatalog.source.local

import androidx.lifecycle.LiveData
import com.rivaldo.moviecatalog.database.Movie
import com.rivaldo.moviecatalog.source.local.room.CatalogDao
import androidx.paging.DataSource
import com.rivaldo.moviecatalog.database.Tv

class LocalDataSource(private val mCatalogDao: CatalogDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(catalogDao: CatalogDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(catalogDao)
    }

    fun getFavoriteMovie(): DataSource.Factory<Int, Movie> = mCatalogDao.getFavoriteMovie()

    fun getFavoriteTv(): DataSource.Factory<Int, Tv> = mCatalogDao.getFavoriteTv()

    fun insertFavoriteTv(tv: Tv) {
        mCatalogDao.insertFavoriteTv(tv)
    }

    fun insertFavoriteMovie(movie: Movie){
        mCatalogDao.insertFavoriteMovie(movie)
    }

    fun checkFavoriteMovie(id: Int) : Boolean {
        return mCatalogDao.checkFavoriteMovie(id)
    }

    fun checkFavoriteTv(id: Int) : Boolean {
        return mCatalogDao.checkFavoriteTv(id)
    }


    fun deleteFavoriteMovie(movie: Movie) {
        mCatalogDao.deleteFavoriteMovie(movie)
    }

    fun deleteFavoriteTv(tv: Tv) {
        mCatalogDao.deleteFavoriteTv(tv)
    }
}