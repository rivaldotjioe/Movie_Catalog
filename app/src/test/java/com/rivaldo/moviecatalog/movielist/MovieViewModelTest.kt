package com.rivaldo.moviecatalog.movielist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.rivaldo.moviecatalog.source.CatalogRepository
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemMoviePopular
import com.rivaldo.moviecatalog.utils.DummyData
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<List<ResultsItemMoviePopular>>

    @Before
    fun setUp(){
        viewModel = MovieViewModel(catalogRepository)
    }

    @Test
    fun getMovie() {
        val dummypopularmovie = DummyData.generateMovieData()
        val datapopularmovie = MutableLiveData<List<ResultsItemMoviePopular>>()
        datapopularmovie.value = dummypopularmovie
        `when`(catalogRepository.getPopularMovie()).thenReturn(datapopularmovie)
        val movie_data = viewModel.getPopularMovie()?.value
        verify(catalogRepository).getPopularMovie()
        assertNotNull(movie_data)
        assertEquals(dummypopularmovie?.size, movie_data?.size)
        viewModel.getPopularMovie()?.observeForever(observer)
        verify(observer).onChanged(dummypopularmovie)
    }
}