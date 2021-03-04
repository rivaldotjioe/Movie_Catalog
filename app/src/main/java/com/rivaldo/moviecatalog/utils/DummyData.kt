package com.rivaldo.moviecatalog.utils

import com.rivaldo.moviecatalog.source.remote.response.MovieDetailResponse
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemMoviePopular
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemPopularTv
import com.rivaldo.moviecatalog.source.remote.response.TvDetailResponse

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

    fun generateMovieDetail(): MovieDetailResponse {
        var detailmovie = MovieDetailResponse(
            originalLanguage = "en",
            imdbId = null,
            video = null,
            title = "Fight Club",
            backdropPath = "/52AfXWuXCHn3UjD17rBruA9f5qb.jpg",
            revenue = null,
            genres = listOf(),
            popularity = 35.427,
            productionCountries = listOf(),
            id = null,
            voteCount = null,
            budget = null,
            overview = "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.",
            originalTitle = null,
            runtime = null,
            posterPath = "/8kNruSfhk5IoE4eZOc4UpvDn6tq.jpg",
            spokenLanguages = listOf(),
            productionCompanies = listOf(),
            releaseDate = null,
            voteAverage = null,
            belongsToCollection = null,
            tagline = null,
            adult = false,
            homepage = null,
            status = null
        )
        return detailmovie
    }

    fun generateTvDetail(): TvDetailResponse {
        var detailtv = TvDetailResponse(
            originalLanguage = "en",
            numberOfEpisodes = null,
            networks = listOf(),
            type = null,
            backdropPath = null,
            genres = listOf(),
            popularity = 5.041,
            productionCountries = listOf(),
            id = null,
            numberOfSeasons = null,
            voteCount = null,
            firstAirDate = null,
            overview = "Till Death Us Do Part is a ground-breaking British sitcom that aired on BBC1 from 1965 to 1975. First airing as a Comedy Playhouse pilot, the show aired in seven series until 1975. Six years later, ITV continued the sitcom, calling it Till Death.... From 1985 to 1992, the BBC produced a sequel In Sickness and in Health.\n\nCreated by Johnny Speight, Till Death Us Do Part centred on the East End Garnett family, led by patriarch Alf Garnett, a reactionary white working-class man who holds racist and anti-socialist views. His gentle and long-suffering wife Else was played by Dandy Nichols, and his daughter Rita by Una Stubbs. Rita's bright but layabout husband Mike Rawlins is a socialist. The character Alf Garnett became a well known character in British culture, and Mitchell played him on stage and television up until 1998, when Speight died.\n\nIn addition to the spin-off In Sickness and in Health, Till Death Us Do Part was re-made in many countries including Brazil, Germany and the United States.\n\nMany episodes from the first three series are thought to no longer exist, having been wiped in the late 1960s and early '70s as was the policy at the time.",
            seasons = listOf(),
            languages = listOf(),
            createdBy = listOf(),
            lastEpisodeToAir = null,
            posterPath = "/5r8enLaWs3SnVoInZYsOLZgboki.jpg",
            originCountry = listOf(),
            spokenLanguages = listOf(),
            productionCompanies = listOf(),
            originalName = "Till Death Us Do Part",
            voteAverage = null,
            name = "Unemployment",
            tagline = null,
            episodeRunTime = listOf(),
            nextEpisodeToAir = null,
            inProduction = null,
            lastAirDate = null,
            homepage = null,
            status = null
        )

        return detailtv
    }


}