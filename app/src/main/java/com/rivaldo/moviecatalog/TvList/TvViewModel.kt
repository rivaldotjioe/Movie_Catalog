package com.rivaldo.moviecatalog.TvList

import androidx.lifecycle.ViewModel
import com.rivaldo.moviecatalog.Data.Tv

class TvViewModel : ViewModel() {

    fun getTvData() : List<Tv> {
    return TvData.generateTvData()
    }
}