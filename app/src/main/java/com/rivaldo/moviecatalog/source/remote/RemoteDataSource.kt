package com.rivaldo.moviecatalog.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rivaldo.moviecatalog.source.remote.response.*
import com.rivaldo.moviecatalog.utils.EspressoIdlingResources
import com.rivaldo.moviecatalog.utils.LiveDataTestUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    private val _moviepopular = MutableLiveData<List<ResultsItemMoviePopular>>()
    val moviepopular : LiveData<List<ResultsItemMoviePopular>> = _moviepopular

    private val _tvpopular = MutableLiveData<List<ResultsItemPopularTv>>()
    val tvPopular : LiveData<List<ResultsItemPopularTv>> = _tvpopular

    private val _movieDetail = MutableLiveData<MovieDetailResponse>()
    val movieDetail : LiveData<MovieDetailResponse> = _movieDetail

    private val _tvDetail = MutableLiveData<TvDetailResponse>()
    val tvDetail : LiveData<TvDetailResponse> = _tvDetail



    companion object {
            private const val TAG = "RemoteDataSource"
            private const val API = "e10b31066b4d9cb3e4e6aa8fefed3b35"

        @Volatile
        private var instance : RemoteDataSource? = null
        @InternalCoroutinesApi
        fun getInstance() : RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    fun testPopularMovie(): List<ResultsItemMoviePopular> {
        GlobalScope.launch(context = Dispatchers.IO) {
            val client = ApiConfig.getApiService().getPopularMovie(RemoteDataSource.API)
            client.enqueue(object : Callback<ResponseMoviePopular> {
                override fun onResponse(
                    call: Call<ResponseMoviePopular>,
                    response: Response<ResponseMoviePopular>
                ) {
                    if (response.isSuccessful) {
                        _moviepopular.value =  response.body()!!.results as List<ResultsItemMoviePopular>
                    } else {
                        Log.e(RemoteDataSource.TAG, "onFailure :  ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ResponseMoviePopular>, t: Throwable) {
                    Log.e(RemoteDataSource.TAG, "onFailure: ${t.message.toString()}")
                }
            })

        }
        return LiveDataTestUtil.getValue(moviepopular)

    }

        fun testPopularTv() : List<ResultsItemPopularTv> {
            GlobalScope.launch(context = Dispatchers.Default) {
                val client = ApiConfig.getApiService().getPopularTv(RemoteDataSource.API)
                client.enqueue(object : Callback<ResponseTvPopular>{
                    override fun onResponse(
                        call: Call<ResponseTvPopular>,
                        response: Response<ResponseTvPopular>
                    ) {

                        if (response.isSuccessful) {
                            _tvpopular.value = response.body()?.results as List<ResultsItemPopularTv>
                        } else {
                            Log.e(RemoteDataSource.TAG, " onFailure : ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<ResponseTvPopular>, t: Throwable) {
                        Log.e(RemoteDataSource.TAG, "onFailure: ${t.message.toString()}")
                    }
                })
            }
            return LiveDataTestUtil.getValue(tvPopular)
        }


    fun getPopularTv(): LiveData<List<ResultsItemPopularTv>> {
        EspressoIdlingResources.increment()
        GlobalScope.launch(context = Dispatchers.Default) {
            val client = ApiConfig.getApiService().getPopularTv(RemoteDataSource.API)
            client.enqueue(object : Callback<ResponseTvPopular>{
                override fun onResponse(
                    call: Call<ResponseTvPopular>,
                    response: Response<ResponseTvPopular>
                ) {

                    if (response.isSuccessful) {
                        _tvpopular.value = response.body()?.results as List<ResultsItemPopularTv>
                    } else {
                        Log.e(RemoteDataSource.TAG, " onFailure : ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ResponseTvPopular>, t: Throwable) {
                    Log.e(RemoteDataSource.TAG, "onFailure: ${t.message.toString()}")
                }
            })
        }
        EspressoIdlingResources.decrement()
        return tvPopular
    }

    fun getPopularMovie(): LiveData<List<ResultsItemMoviePopular>> {
        EspressoIdlingResources.increment()
        GlobalScope.launch(context = Dispatchers.IO) {
            val client = ApiConfig.getApiService().getPopularMovie(RemoteDataSource.API)
            client.enqueue(object : Callback<ResponseMoviePopular> {
                override fun onResponse(
                    call: Call<ResponseMoviePopular>,
                    response: Response<ResponseMoviePopular>
                ) {
                    if (response.isSuccessful) {
                   _moviepopular.value =  response.body()?.results as List<ResultsItemMoviePopular>
                    } else {
                        Log.e(RemoteDataSource.TAG, "onFailure :  ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ResponseMoviePopular>, t: Throwable) {
                    Log.e(RemoteDataSource.TAG, "onFailure: ${t.message.toString()}")
                }
            })

        }
        EspressoIdlingResources.decrement()
        return moviepopular

    }

    fun getDetailMovie(id: Int) : LiveData<MovieDetailResponse> {
        EspressoIdlingResources.increment()
        GlobalScope.launch(context = Dispatchers.IO) {
            val client = ApiConfig.getApiService().getMovieDetail(id,RemoteDataSource.API)
            client.enqueue(object : Callback<MovieDetailResponse> {
                override fun onResponse(call: Call<MovieDetailResponse>, response: Response<MovieDetailResponse>) {
                    if (response.isSuccessful) {
                        _movieDetail.value = response.body()
                    } else {
                        Log.e(RemoteDataSource.TAG, "onFailure : ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                    Log.e(RemoteDataSource.TAG, "onFailure : ${t.message.toString()}")
                }
            })
        }
        EspressoIdlingResources.decrement()
        return movieDetail
    }

    fun getDetailTv(id: Int) : LiveData<TvDetailResponse> {
        EspressoIdlingResources.increment()
        GlobalScope.launch(context = Dispatchers.IO) {
            val client = ApiConfig.getApiService().getTvDetail(id, RemoteDataSource.API)
            client.enqueue(object  : Callback<TvDetailResponse> {
                override fun onResponse(
                    call: Call<TvDetailResponse>,
                    response: Response<TvDetailResponse>
                ) {
                    if (response.isSuccessful) {
                        _tvDetail.value = response.body()
                    } else {
                        Log.e(RemoteDataSource.TAG, "onFailure Response : ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<TvDetailResponse>, t: Throwable) {
                    Log.e(RemoteDataSource.TAG, "onFailure : ${t.message.toString()}")
                }
            })
        }
        EspressoIdlingResources.decrement()
        return tvDetail
    }
}