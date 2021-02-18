package com.rivaldo.moviecatalog.utils

import com.rivaldo.moviecatalog.source.remote.response.ResultsItemPopularTv

interface TvItemClicked {

    fun onTvItemClicked(data: ResultsItemPopularTv)
}