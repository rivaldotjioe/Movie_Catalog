package com.rivaldo.moviecatalog.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.rivaldo.moviecatalog.database.Movie
import com.rivaldo.moviecatalog.database.Tv
import com.rivaldo.moviecatalog.source.local.LocalDataSource
import com.rivaldo.moviecatalog.source.remote.response.RemoteDataSource
import com.rivaldo.moviecatalog.source.remote.response.MovieDetailResponse
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemMoviePopular
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemPopularTv
import com.rivaldo.moviecatalog.source.remote.response.TvDetailResponse
import com.rivaldo.moviecatalog.tvlist.TvData
import com.rivaldo.moviecatalog.utils.AppExecutors
import com.rivaldo.moviecatalog.utils.DummyData
import com.rivaldo.moviecatalog.utils.MovieData
import com.rivaldo.moviecatalog.utils.PagedListUtil
import com.rivaldo.moviecatalog.vo.Resource
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CatalogRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val catalogRepository = FakeCatalogRepository(remote, local, appExecutors)

    private val popularmovie = DummyData.generateMovieData()
    private val populartv = DummyData.generateTvData()
    private val detailmovie = DummyData.generateMovieDetail()
    private val detailtv = DummyData.generateTvDetail()
    private val dummyfavoritemovie = MovieData.generateMovieData()
    private val dummyfavoritetv = TvData.generateTvData()


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

    @Test
    fun getFavoriteMovie() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Movie>
        `when`(local.getFavoriteMovie()).thenReturn(dataSourceFactory)
        catalogRepository.getFavoriteMovie()
        val favoritemovie = Resource.success(PagedListUtil.mockPagedList(MovieData.generateMovieData()))
        verify(local).getFavoriteMovie()
        assertNotNull(favoritemovie.data)
        assertEquals(dummyfavoritemovie.size.toLong(), favoritemovie.data?.size?.toLong())
    }

    @Test
    fun getFavoriteTv(){
        val datasourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Tv>
        `when`(local.getFavoriteTv()).thenReturn(datasourceFactory)
        catalogRepository.getFavoriteTv()
        val favoritetv = Resource.success(PagedListUtil.mockPagedList(TvData.generateTvData()))
        verify(local).getFavoriteTv()
        assertNotNull(favoritetv.data)
        assertEquals(dummyfavoritetv.size.toLong(), favoritetv.data?.size?.toLong())
    }
}