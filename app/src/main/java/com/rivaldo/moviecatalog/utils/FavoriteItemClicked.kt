package com.rivaldo.moviecatalog.utils

import com.rivaldo.moviecatalog.database.Movie

interface FavoriteItemClicked {
    fun onMovieDeleteClicked(movie: Movie)

}