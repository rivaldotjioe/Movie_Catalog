package com.rivaldo.moviecatalog.utils

import com.rivaldo.moviecatalog.source.remote.response.ResultsItemMoviePopular

interface MovieItemClicked {
    fun onItemMovieClicked(data: ResultsItemMoviePopular)


}