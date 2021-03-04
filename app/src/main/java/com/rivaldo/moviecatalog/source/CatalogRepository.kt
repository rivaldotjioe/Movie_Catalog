package com.rivaldo.moviecatalog.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.rivaldo.moviecatalog.database.Movie
import com.rivaldo.moviecatalog.database.Tv
import com.rivaldo.moviecatalog.source.local.LocalDataSource
import com.rivaldo.moviecatalog.source.remote.response.RemoteDataSource
import com.rivaldo.moviecatalog.source.remote.response.MovieDetailResponse
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemMoviePopular
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemPopularTv
import com.rivaldo.moviecatalog.source.remote.response.TvDetailResponse
import com.rivaldo.moviecatalog.utils.AppExecutors
import com.rivaldo.moviecatalog.utils.EspressoIdlingResources
import kotlin.properties.Delegates

class CatalogRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
    ) : DataSourceInterface {

    companion object {
        @Volatile
        private var instance : CatalogRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource, appExecutors: AppExecutors) : CatalogRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogRepository(remoteDataSource, localDataSource, appExecutors)
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

    override fun getFavoriteMovie(): LiveData<PagedList<Movie>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovie(), config).build()
    }

    override fun getFavoriteTv(): LiveData<PagedList<Tv>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTv(), config).build()
    }

    override fun insertFavoriteMovie(movie: Movie) {
        EspressoIdlingResources.increment()
        appExecutors.diskIO().execute {  localDataSource.insertFavoriteMovie(movie) }
        EspressoIdlingResources.decrement()
    }

    override fun insertFavoriteTv(tv: Tv) {
        EspressoIdlingResources.increment()
        appExecutors.diskIO().execute { localDataSource.insertFavoriteTv(tv) }
        EspressoIdlingResources.decrement()
    }

    override fun checkFavoriteMovie(id: Int): Boolean {
        return localDataSource.checkFavoriteMovie(id)
    }

    override fun checkFavoriteTv(id: Int): Boolean {
        return localDataSource.checkFavoriteTv(id)
    }

    override fun deleteFavoriteMovie(movie: Movie) {
        localDataSource.deleteFavoriteMovie(movie)
    }

    override fun deleteFavoriteTv(tv: Tv) {
        localDataSource.deleteFavoriteTv(tv)
    }


}