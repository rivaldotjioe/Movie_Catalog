package com.rivaldo.moviecatalog.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rivaldo.moviecatalog.database.Movie
import com.rivaldo.moviecatalog.database.Tv
import com.rivaldo.moviecatalog.source.CatalogRepository
import com.rivaldo.moviecatalog.source.remote.response.MovieDetailResponse
import com.rivaldo.moviecatalog.source.remote.response.TvDetailResponse
import com.rivaldo.moviecatalog.utils.LiveDataTestUtil

class DetailViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    var detailmovie : LiveData<MovieDetailResponse>? = null
    var detailtv : LiveData<TvDetailResponse>? = null

    companion object{
        private lateinit var detailData : Any

        public fun setData(item:Any){
            this.detailData = item
        }
    }

    fun getDetailMovie(id: Int): LiveData<MovieDetailResponse>? {
            detailmovie = catalogRepository.getDetailMovie(id)
        return detailmovie
    }

    fun getDetailTv(id: Int): LiveData<TvDetailResponse>? {
        detailtv = catalogRepository.getDetailTv(id)
        return detailtv
    }

    fun getData() : Any{
        return detailData
    }

    fun insertFavoriteMovie(movie: Movie) {
        catalogRepository.insertFavoriteMovie(movie)
    }

    fun insertFavoriteTv(tv: Tv) {
        catalogRepository.insertFavoriteTv(tv)
    }

    fun checkFavoriteMovie(id: Int) : Boolean {
        return catalogRepository.checkFavoriteMovie(id)
    }

    fun checkFavoriteTv(id: Int): Boolean {
        return catalogRepository.checkFavoriteTv(id)
    }
}