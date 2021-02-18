package com.rivaldo.moviecatalog.detail

import com.rivaldo.moviecatalog.movielist.MovieData
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemMoviePopular
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemPopularTv
import com.rivaldo.moviecatalog.tvlist.TvData
import com.rivaldo.moviecatalog.utils.DummyData
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    @Mock
    private lateinit var detailViewModel: DetailViewModel
    val moviedata = DummyData.generateMovieData()?.get(0) as ResultsItemMoviePopular
    val tvData = DummyData.generateTvData()?.get(0) as ResultsItemPopularTv
    @Before
    fun setUp(){

    }

    @Test
    fun getDetailMovie() {
        `when`(detailViewModel.getData()).thenReturn(moviedata)
        val data = detailViewModel.getData() as ResultsItemMoviePopular
        assertNotNull(data)
        assertEquals(moviedata.title, data.title)
    }

    @Test
    fun getDetailTv() {
        `when`(detailViewModel.getData()).thenReturn(tvData)
        val data = detailViewModel.getData() as ResultsItemPopularTv
        assertNotNull(data)
        assertEquals(tvData.name, data.name)

    }
}