package com.rivaldo.moviecatalog.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rivaldo.moviecatalog.source.CatalogRepository
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemMoviePopular

@Suppress("UNCHECKED_CAST")
class MovieViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    private var _moviepopular = MutableLiveData<List<ResultsItemMoviePopular>>()
     var moviepopular: LiveData<List<ResultsItemMoviePopular>>? = null

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "MovieViewModel"
            private const val API = "e10b31066b4d9cb3e4e6aa8fefed3b35"
    }

   fun getPopularMovie(): LiveData<List<ResultsItemMoviePopular>>? {
        _isLoading.value = true
           moviepopular  = catalogRepository.getPopularMovie()
       _isLoading.value = false
       return moviepopular
        }



    }
