package com.rivaldo.moviecatalog.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rivaldo.moviecatalog.source.remote.RemoteDataSource
import com.rivaldo.moviecatalog.source.remote.response.MovieDetailResponse
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemMoviePopular
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemPopularTv
import com.rivaldo.moviecatalog.source.remote.response.TvDetailResponse
import kotlin.coroutines.coroutineContext

class CatalogRepository private constructor(private val remoteDataSource: RemoteDataSource) : DataSourceInterface {

    companion object {
        @Volatile
        private var instance : CatalogRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource) : CatalogRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogRepository(remoteDataSource)
            }
    }

    override fun getPopularMovie(): LiveData<List<ResultsItemMoviePopular>> {
        val movies = remoteDataSource.getPopularMovie()
        return movies
    }

    override fun getPopularTv(): LiveData<List<ResultsItemPopularTv>> {
        val tv = remoteDataSource.getPopularTv()
        return tv
    }

    override fun getDetailMovie(id: Int): LiveData<MovieDetailResponse> {
        val detailmovie = remoteDataSource.getDetailMovie(id)
        return detailmovie
    }

    override fun getDetailTv(id: Int): LiveData<TvDetailResponse> {
        val detailtv = remoteDataSource.getDetailTv(id)
        return detailtv
    }


}