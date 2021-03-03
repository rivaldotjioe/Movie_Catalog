package com.rivaldo.moviecatalog.injection

import android.content.Context
import com.rivaldo.moviecatalog.source.CatalogRepository
import com.rivaldo.moviecatalog.source.local.LocalDataSource
import com.rivaldo.moviecatalog.source.local.room.MovieCatalogDatabase
import com.rivaldo.moviecatalog.source.remote.response.RemoteDataSource
import com.rivaldo.moviecatalog.utils.AppExecutors
import kotlinx.coroutines.InternalCoroutinesApi

object Injection {

    @InternalCoroutinesApi
    fun provideRepository(context: Context): CatalogRepository {
        val database = MovieCatalogDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.movieCatalogDao())
        val appExecutors = AppExecutors()
        return CatalogRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}