package com.rivaldo.moviecatalog.favorite.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.rivaldo.moviecatalog.database.Tv
import com.rivaldo.moviecatalog.source.CatalogRepository

class FavoriteTvViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {
    var tvfavorite : LiveData<PagedList<Tv>>? = null

    companion object {
        private const val TAG = "FavoriteTvViewModel"
    }

    fun getFavoriteTv() : LiveData<PagedList<Tv>>? {
        tvfavorite = catalogRepository.getFavoriteTv()
        return tvfavorite
    }

    fun deleteFavoriteTv(tv: Tv) {
        catalogRepository.deleteFavoriteTv(tv)
    }
}