package com.rivaldo.moviecatalog.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.any
import com.rivaldo.moviecatalog.source.CatalogRepository
import com.rivaldo.moviecatalog.source.remote.response.MovieDetailResponse
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemMoviePopular
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemPopularTv
import com.rivaldo.moviecatalog.source.remote.response.TvDetailResponse
import com.rivaldo.moviecatalog.utils.DummyData
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var detailViewModel: DetailViewModel

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observermoviedetail: Observer<MovieDetailResponse>

    @Mock
    private lateinit var observertvdetail: Observer<TvDetailResponse>

    val moviedata = DummyData.generateMovieData()?.get(0) as ResultsItemMoviePopular
    val tvData = DummyData.generateTvData()?.get(0) as ResultsItemPopularTv
    @Before
    fun setUp(){
        detailViewModel = DetailViewModel(catalogRepository)
    }

    @Test
    fun getDetailMovie() {
        val dummydetailmovie = DummyData.generateMovieDetail()
        val datadetailmovie = MutableLiveData<MovieDetailResponse>()
        datadetailmovie.value = dummydetailmovie
        `when`(catalogRepository.getDetailMovie(any())).thenReturn(datadetailmovie)
        val data = detailViewModel.getDetailMovie(550)?.value
        verify(catalogRepository).getDetailMovie(550)
        assertNotNull(data)
        assertEquals(dummydetailmovie.title, data?.title)
        detailViewModel.getDetailMovie(550)?.observeForever(observermoviedetail)
        verify(observermoviedetail).onChanged(dummydetailmovie)
    }

    @Test
    fun getDetailTv() {
        val dummydetailtv = DummyData.generateTvDetail()
        val datadetailtv = MutableLiveData<TvDetailResponse>()
        datadetailtv.value = dummydetailtv
        `when`(catalogRepository.getDetailTv(550)).thenReturn(datadetailtv)
        val data = detailViewModel.getDetailTv(550)?.value
        verify(catalogRepository).getDetailTv(550)
        assertNotNull(data)
        assertEquals(dummydetailtv.name, data?.name)
        detailViewModel.getDetailTv(550)?.observeForever(observertvdetail)
        verify(observertvdetail).onChanged(dummydetailtv)

    }
}