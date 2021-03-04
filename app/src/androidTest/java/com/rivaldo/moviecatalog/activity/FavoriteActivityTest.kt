package com.rivaldo.moviecatalog.activity

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.internal.inject.InstrumentationContext
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.rivaldo.moviecatalog.R
import com.rivaldo.moviecatalog.database.Movie
import com.rivaldo.moviecatalog.detail.DetailViewModel
import com.rivaldo.moviecatalog.favorite.movie.FavoriteMovieViewModel
import com.rivaldo.moviecatalog.injection.Injection
import com.rivaldo.moviecatalog.source.CatalogRepository
import com.rivaldo.moviecatalog.source.local.LocalDataSource
import com.rivaldo.moviecatalog.source.local.room.CatalogDao
import com.rivaldo.moviecatalog.utils.EspressoIdlingResources
import com.rivaldo.moviecatalog.viewmodel.ViewModelFactory
import kotlinx.coroutines.InternalCoroutinesApi
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class FavoriteActivityTest {

    lateinit var instrumentationContext: Context
    lateinit var viewModelFavoriteMovie : FavoriteMovieViewModel
    lateinit var catalogRepository: CatalogRepository

    private var favoritemovie : LiveData<PagedList<Movie>>? = null
    @InternalCoroutinesApi
    @Before
    fun setUp() {
        ActivityScenario.launch(FavoriteActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResources.espressoTestIdlingResource)
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
//        catalogRepository = Injection.provideRepository(instrumentationContext)
//        viewModelFavoriteMovie = FavoriteMovieViewModel(catalogRepository)
//        favoritemovie = viewModelFavoriteMovie.getFavoriteMovie()
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.espressoTestIdlingResource)
    }

    @Test
    fun loadMovieFavorite() {
        onView(withId(R.id.listfavoritemovie)).check(matches(isDisplayed()))
        onView(withId(R.id.listfavoritemovie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
    }

    @Test
    fun loadTvFavorite() {
        onView(withText("TV")).perform(click())
        onView(withId(R.id.listfavoritetv)).check(matches(isDisplayed()))
        onView(withId(R.id.listfavoritetv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
    }


}