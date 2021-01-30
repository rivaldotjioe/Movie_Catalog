package com.rivaldo.moviecatalog.TvList

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class TvViewModelTest {

    private lateinit var viewModel: TvViewModel

    @Before
    fun setUp() {
        viewModel = TvViewModel()
    }

    @Test
    fun getTvData() {
        val tv_data = viewModel.getTvData()
        assertNotNull(tv_data)
        assertEquals(2,tv_data.size)
    }
}