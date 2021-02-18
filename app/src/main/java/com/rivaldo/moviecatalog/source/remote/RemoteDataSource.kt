package com.rivaldo.moviecatalog.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rivaldo.moviecatalog.source.remote.response.ResponseMoviePopular
import com.rivaldo.moviecatalog.source.remote.response.ResponseTvPopular
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemMoviePopular
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemPopularTv
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
}