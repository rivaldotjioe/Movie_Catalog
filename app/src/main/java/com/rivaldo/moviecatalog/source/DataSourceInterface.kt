package com.rivaldo.moviecatalog.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rivaldo.moviecatalog.source.remote.response.MovieDetailResponse
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemMoviePopular
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemPopularTv
import com.rivaldo.moviecatalog.source.remote.response.TvDetailResponse

interface DataSourceInterface {
    fun getPopularMovie() : LiveData<List<ResultsItemMoviePopular>>

    fun getPopularTv(): LiveData<List<ResultsItemPopularTv>>

    fun getDetailMovie(id:Int) : LiveData<MovieDetailResponse>

    fun getDetailTv(id:Int) : LiveData<TvDetailResponse>
}