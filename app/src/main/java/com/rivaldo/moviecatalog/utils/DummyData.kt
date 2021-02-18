package com.rivaldo.moviecatalog.utils

import com.rivaldo.moviecatalog.source.remote.response.ResultsItemMoviePopular
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemPopularTv

object DummyData {

    fun generateMovieData() : MutableList<ResultsItemMoviePopular>? {
        var moviedata : MutableList<ResultsItemMoviePopular>? = mutableListOf()
        moviedata?.add(0, ResultsItemMoviePopular(overview = "From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences.",
                originalLanguage = "en",
                originalTitle = "Suicide Squad",
                video = false,
                title = "Suicide Squad",
                genreIds = listOf(28,
                        53),
                posterPath = "/lFSSLTlFozwpaGlO31OoUeirBgQ.jpg",
                backdropPath = "/ndlQ2Cuc3cjTL7lTynw6I4boP4S.jpg",
                releaseDate = "2016-08-03",
                popularity = 48.261451,
                voteAverage = 5.91,
                id = 297761,
                adult = false,
                voteCount = 1466))

        return moviedata
    }

    fun generateTvData(): MutableList<ResultsItemPopularTv>? {
        var tvdata : MutableList<ResultsItemPopularTv>? = mutableListOf()

        tvdata?.add(0, ResultsItemPopularTv(firstAirDate = "2010-06-08",
                overview = "Based on the Pretty Little Liars series of young adult novels by Sara Shepard, the series follows the lives of four girls — Spencer, Hanna, Aria, and Emily — whose clique falls apart after the disappearance of their queen bee, Alison. One year later, they begin receiving messages from someone using the name \"A\" who threatens to expose their secrets — including long-hidden ones they thought only Alison knew.",
                originalLanguage = "en",
                genreIds = listOf( 18,
                        9648),
                posterPath = "/vC324sdfcS313vh9QXwijLIHPJp.jpg",
                originCountry = listOf("US"),
                backdropPath = "/rQGBjWNveVeF8f2PGRtS85w9o9r.jpg",
                popularity = 47.432451,
                voteAverage = 5.04,
                originalName = "Pretty Little Liars",
                name = "Pretty Little Liars",
                id = 31917,
                voteCount = 133))
        return tvdata
    }


}