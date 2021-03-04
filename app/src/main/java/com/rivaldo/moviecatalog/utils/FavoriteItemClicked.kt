package com.rivaldo.moviecatalog.utils

import com.rivaldo.moviecatalog.database.Movie
import com.rivaldo.moviecatalog.database.Tv

interface FavoriteItemClicked {
    fun onMovieDeleteClicked(movie: Movie)

    fun onTvDeleteClicked(tv: Tv)
}