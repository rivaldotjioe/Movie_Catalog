package com.rivaldo.moviecatalog.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemMoviePopular
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemPopularTv

interface DataSourceInterface {
    fun getPopularMovie() : LiveData<List<ResultsItemMoviePopular>>

    fun getPopularTv(): LiveData<List<ResultsItemPopularTv>>
}