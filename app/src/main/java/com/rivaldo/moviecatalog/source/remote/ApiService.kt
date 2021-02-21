package com.rivaldo.moviecatalog.source.remote

import com.rivaldo.moviecatalog.source.remote.response.MovieDetailResponse
import com.rivaldo.moviecatalog.source.remote.response.ResponseMoviePopular
import com.rivaldo.moviecatalog.source.remote.response.ResponseTvPopular
import com.rivaldo.moviecatalog.source.remote.response.TvDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getPopularMovie(@Query("api_key") api_key: String): Call<ResponseMoviePopular>

    @GET("tv/popular")
    fun getPopularTv(@Query("api_key") api_key: String): Call<ResponseTvPopular>

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movie_id: Int, @Query("api_key") api_key: String): Call<MovieDetailResponse>

    @GET("tv/{tv_id}")
    fun getTvDetail(@Path("tv_id") tv_id: Int, @Query("api_key") api_key: String): Call<TvDetailResponse>
}