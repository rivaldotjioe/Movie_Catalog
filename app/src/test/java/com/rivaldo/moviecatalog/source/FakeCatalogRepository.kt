package com.rivaldo.moviecatalog.source

import androidx.lifecycle.LiveData
import com.rivaldo.moviecatalog.source.CatalogRepository
import com.rivaldo.moviecatalog.source.DataSourceInterface
import com.rivaldo.moviecatalog.source.remote.RemoteDataSource
import com.rivaldo.moviecatalog.source.remote.response.MovieDetailResponse
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemMoviePopular
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemPopularTv
import com.rivaldo.moviecatalog.source.remote.response.TvDetailResponse

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

    override fun getDetailMovie(id: Int): LiveData<MovieDetailResponse> {
       val moviedetail = remoteDataSource.getDetailMovie(id)
        return moviedetail
    }

    override fun getDetailTv(id: Int): LiveData<TvDetailResponse> {
       val tvdetail = remoteDataSource.getDetailTv(id)
        return tvdetail
    }


}