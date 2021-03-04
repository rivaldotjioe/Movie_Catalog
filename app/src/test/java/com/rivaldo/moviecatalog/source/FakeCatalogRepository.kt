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

class FakeCatalogRepository(private val remoteDataSource: RemoteDataSource,
                            private val localDataSource: LocalDataSource,
                            private val appExecutors: AppExecutors
) :
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
        appExecutors.diskIO().execute {  localDataSource.insertFavoriteMovie(movie) }
    }

    override fun insertFavoriteTv(tv: Tv) {
        appExecutors.diskIO().execute { localDataSource.insertFavoriteTv(tv) }
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