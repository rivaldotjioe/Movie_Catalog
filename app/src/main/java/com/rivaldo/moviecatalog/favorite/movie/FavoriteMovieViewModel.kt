package com.rivaldo.moviecatalog.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rivaldo.moviecatalog.database.Movie
import com.rivaldo.moviecatalog.source.CatalogRepository

class FavoriteMovieViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {
    private var _moviefavorite = MutableLiveData<List<Movie>>()
    var moviefavorite: LiveData<List<Movie>>? = null

    companion object {
        private const val TAG = "FavoriteMovieViewModel"
    }

    fun getFavoriteMovie(): LiveData<List<Movie>>? {
        moviefavorite = catalogRepository.getFavoriteMovie()
        return moviefavorite
    }

    fun deleteFavoriteMovie(movie: Movie) {
        catalogRepository.deleteFavoriteMovie(movie)
    }
}