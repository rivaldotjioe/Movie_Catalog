package com.rivaldo.moviecatalog.tvlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rivaldo.moviecatalog.database.Tv
import com.rivaldo.moviecatalog.source.CatalogRepository
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemPopularTv

class TvViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    private val _tvpopular = MutableLiveData<List<ResultsItemPopularTv>>()
    var tvPopular : LiveData<List<ResultsItemPopularTv>>? = null

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "TvViewModel"
        private const val API = "e10b31066b4d9cb3e4e6aa8fefed3b35"
    }

    fun getPopularTv(): LiveData<List<ResultsItemPopularTv>>? {
       _isLoading.value = true
        tvPopular =  catalogRepository.getPopularTv()
        _isLoading.value = false
        return tvPopular
    }

    fun getTvData() : List<Tv> {
    return TvData.generateTvData()
    }
}