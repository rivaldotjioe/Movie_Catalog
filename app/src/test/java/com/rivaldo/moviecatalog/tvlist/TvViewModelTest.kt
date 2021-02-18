package com.rivaldo.moviecatalog.tvlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.rivaldo.moviecatalog.movielist.MovieViewModel
import com.rivaldo.moviecatalog.source.CatalogRepository
import com.rivaldo.moviecatalog.source.remote.RemoteDataSource
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemMoviePopular
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemPopularTv
import com.rivaldo.moviecatalog.utils.DummyData
import com.rivaldo.moviecatalog.utils.LiveDataTestUtil
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvViewModelTest {

    private lateinit var viewModel: TvViewModel

    @Mock
    private lateinit var catalogRepository : CatalogRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<List<ResultsItemPopularTv>>

    @Before
    fun setUp(){
        viewModel = TvViewModel(catalogRepository)

    }

    @Test
    fun getTvData() {
        val dummypopulartv = DummyData.generateTvData()
        val datapopulartv = MutableLiveData<List<ResultsItemPopularTv>>()
        datapopulartv.value = dummypopulartv
        `when`(catalogRepository.getPopularTv()).thenReturn(datapopulartv)
        val tv_data = viewModel.getPopularTv()?.value
        verify(catalogRepository).getPopularTv()
        assertNotNull(tv_data)
        assertEquals(dummypopulartv?.size, tv_data?.size)
        viewModel.getPopularTv()?.observeForever(observer)
        verify(observer).onChanged(dummypopulartv)
    }
}