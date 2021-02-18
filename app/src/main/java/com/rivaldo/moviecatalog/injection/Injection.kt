package com.rivaldo.moviecatalog.injection

import android.content.Context
import com.rivaldo.moviecatalog.source.CatalogRepository
import com.rivaldo.moviecatalog.source.remote.RemoteDataSource
import kotlinx.coroutines.InternalCoroutinesApi

object Injection {

    @InternalCoroutinesApi
    fun provideRepository(context: Context): CatalogRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return CatalogRepository.getInstance(remoteDataSource)
    }
}