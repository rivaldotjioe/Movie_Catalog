package com.rivaldo.moviecatalog.favorite.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.rivaldo.moviecatalog.database.Tv
import com.rivaldo.moviecatalog.source.CatalogRepository
import com.rivaldo.moviecatalog.tvlist.TvData
import com.rivaldo.moviecatalog.utils.PagedListUtil
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class FavoriteTvViewModelTest {

    private lateinit var viewModel: FavoriteTvViewModel

    @Mock
    private lateinit var observer: Observer<PagedList<Tv>>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var pagedList: PagedList<Tv>

    @Mock
    private lateinit var catalogRepository: CatalogRepository


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = FavoriteTvViewModel(catalogRepository)
    }

    @Test
    fun getFavoriteTv() {
        val dummytv = PagedListUtil.mockPagedList(TvData.generateTvData())
        pagedList = dummytv
        `when`(dummytv.size).thenReturn(10)
        val favoritetv = MutableLiveData<PagedList<Tv>>()
        favoritetv.value = dummytv
        `when`(catalogRepository.getFavoriteTv()).thenReturn(favoritetv)
        val favoritedatatv = viewModel.getFavoriteTv()?.value
        verify(catalogRepository).getFavoriteTv()
        assertNotNull(favoritedatatv)
        assertEquals(10, favoritedatatv?.size)
        viewModel.getFavoriteTv()?.observeForever(observer)
        verify(observer).onChanged(dummytv)
    }
}