package com.rivaldo.moviecatalog.favorite.movie

import android.graphics.pdf.PdfDocument
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import com.rivaldo.moviecatalog.database.Movie
import com.rivaldo.moviecatalog.source.CatalogRepository
import com.rivaldo.moviecatalog.utils.MovieData
import com.rivaldo.moviecatalog.utils.PagedListUtil
import com.rivaldo.moviecatalog.vo.Resource
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import java.util.*

class FavoriteMovieViewModelTest {

    private lateinit var viewModel : FavoriteMovieViewModel
    @Mock
    private lateinit var observer: Observer<PagedList<Movie>>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var pagedList: PagedList<Movie>

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = FavoriteMovieViewModel(catalogRepository)
    }

    @Test
    fun getFavoriteMovie() {
        val dummyMovie = PagedListUtil.mockPagedList(MovieData.generateMovieData())
        pagedList = dummyMovie
        `when`(dummyMovie.size).thenReturn(10)
        val favoritemovies = MutableLiveData<PagedList<Movie>>()
        favoritemovies.value = dummyMovie
        `when`(catalogRepository.getFavoriteMovie()).thenReturn(favoritemovies)
        val favoritedatamovie = viewModel.getFavoriteMovie()?.value
        verify(catalogRepository).getFavoriteMovie()
        assertNotNull(favoritedatamovie)
        assertEquals(10, favoritedatamovie?.size)

        viewModel.getFavoriteMovie()?.observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}