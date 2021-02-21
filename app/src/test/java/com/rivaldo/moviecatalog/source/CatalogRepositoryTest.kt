package com.rivaldo.moviecatalog.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.rivaldo.moviecatalog.source.remote.RemoteDataSource
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
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CatalogRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val catalogRepository = FakeCatalogRepository(remote)

    private val popularmovie = DummyData.generateMovieData()
    private val populartv = DummyData.generateTvData()
    private val detailmovie = DummyData.generateMovieDetail()
    private val detailtv = DummyData.generateTvDetail()


    @Test
    fun getPopularMovie() {
        val datapopularmovie = MutableLiveData<List<ResultsItemMoviePopular>>()
        datapopularmovie.value = popularmovie
        `when`(remote.getPopularMovie()).thenReturn(datapopularmovie)
        val datamovie = catalogRepository.getPopularMovie()
        verify(remote).getPopularMovie()
        assertEquals(popularmovie?.size, datamovie.value?.size)
    }

    @Test
    fun getPopularTv() {
        val datapopulartv = MutableLiveData<List<ResultsItemPopularTv>>()
        datapopulartv.value = populartv
        `when`(remote.getPopularTv()).thenReturn(datapopulartv)
        val datatv = catalogRepository.getPopularTv()
        verify(remote).getPopularTv()
        assertEquals(populartv?.size, datatv.value?.size)
    }

    @Test
    fun getDetailMovie(){
        val datadetailmovie = MutableLiveData<MovieDetailResponse>()
        datadetailmovie.value = detailmovie
        `when`(remote.getDetailMovie(any())).thenReturn(datadetailmovie)
        val detailmovie = catalogRepository.getDetailMovie(550)
        verify(remote).getDetailMovie(550)
        assertEquals(this.detailmovie.title, detailmovie.value?.title)
    }
    @Test
    fun getDetailTv(){
        val datadetailtv = MutableLiveData<TvDetailResponse>()
        datadetailtv.value = detailtv
        `when`(remote.getDetailTv(any())).thenReturn(datadetailtv)
        val detailtv = catalogRepository.getDetailTv(550)
        verify(remote).getDetailTv(550)
        assertEquals(this.detailtv.name, detailtv.value?.name)
    }
}