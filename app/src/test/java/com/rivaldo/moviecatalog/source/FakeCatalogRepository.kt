package com.rivaldo.moviecatalog.source

import androidx.lifecycle.LiveData
import com.rivaldo.moviecatalog.source.CatalogRepository
import com.rivaldo.moviecatalog.source.DataSourceInterface
import com.rivaldo.moviecatalog.source.remote.RemoteDataSource
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemMoviePopular
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemPopularTv

class FakeCatalogRepository(private val remoteDataSource: RemoteDataSource) :
    DataSourceInterface {

    override fun getPopularMovie(): LiveData<List<ResultsItemMoviePopular>> {
        val movies = remoteDataSource.getPopularMovie()
        return movies
    }

    override fun getPopularTv(): LiveData<List<ResultsItemPopularTv>> {
        val tv = remoteDataSource.getPopularTv()
        return tv
    }



}