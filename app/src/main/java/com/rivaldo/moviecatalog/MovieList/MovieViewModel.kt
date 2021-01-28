package com.rivaldo.moviecatalog.MovieList

import androidx.lifecycle.ViewModel
import com.rivaldo.moviecatalog.Data.Movie

class MovieViewModel : ViewModel() {

    fun getMovie(): List<Movie>{
        return MovieData.generateMovieData()
    }
}