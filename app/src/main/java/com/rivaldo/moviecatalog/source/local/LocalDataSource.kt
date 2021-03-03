package com.rivaldo.moviecatalog.source.local

import androidx.lifecycle.LiveData
import com.rivaldo.moviecatalog.database.Movie
import com.rivaldo.moviecatalog.source.local.room.CatalogDao

class LocalDataSource private constructor(private val mCatalogDao: CatalogDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(catalogDao: CatalogDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(catalogDao)
    }

    fun getFavoriteMovie(): LiveData<List<Movie>> = mCatalogDao.getFavoriteMovie()

    fun insertFavoriteMovie(movie: Movie){
        mCatalogDao.insertFavoriteMovie(movie)
    }

    fun checkFavoriteMovie(id: Int) : Boolean {
        return mCatalogDao.checkFavoriteMovie(id)
    }

    fun deleteFavoriteMovie(movie: Movie) {
        mCatalogDao.deleteFavoriteMovie(movie)
    }
}