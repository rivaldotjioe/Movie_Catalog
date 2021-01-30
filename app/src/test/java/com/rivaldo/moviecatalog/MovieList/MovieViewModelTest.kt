package com.rivaldo.moviecatalog.MovieList

import com.rivaldo.moviecatalog.Data.Movie
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp(){
        viewModel = MovieViewModel()
    }

    @Test
    fun getMovie() {
        val movie_data = viewModel.getMovie()
        assertNotNull(movie_data)
        assertEquals(2, movie_data.size)
    }
}