package com.rivaldo.moviecatalog.source.remote

import com.rivaldo.moviecatalog.source.remote.response.ResponseMoviePopular
import com.rivaldo.moviecatalog.source.remote.response.ResponseTvPopular
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getPopularMovie(@Query("api_key") api_key: String): Call<ResponseMoviePopular>

    @GET("tv/popular")
    fun getPopularTv(@Query("api_key") api_key: String): Call<ResponseTvPopular>
}