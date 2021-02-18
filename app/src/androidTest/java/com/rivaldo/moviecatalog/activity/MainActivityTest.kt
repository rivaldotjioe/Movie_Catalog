package com.rivaldo.moviecatalog.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.rivaldo.moviecatalog.movielist.MovieData
import com.rivaldo.moviecatalog.R
import com.rivaldo.moviecatalog.source.remote.RemoteDataSource
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemMoviePopular
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemPopularTv
import com.rivaldo.moviecatalog.tvlist.TvData
import com.rivaldo.moviecatalog.utils.EspressoIdlingResources
import com.rivaldo.moviecatalog.utils.LiveDataTestUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Before

import org.junit.Test

class MainActivityTest {

    private val remote = RemoteDataSource()
    private lateinit var dataMovie : List<ResultsItemMoviePopular>
    private lateinit var dataTv : List<ResultsItemPopularTv>

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResources.espressoTestIdlingResource)
            dataMovie = remote.testPopularMovie()
            dataTv = remote.testPopularTv()
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.espressoTestIdlingResource)
    }

    @Test
    fun loadDataMovie(){
        onView(withId(R.id.list)).check(matches(isDisplayed()))
        onView(withId(R.id.list)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataMovie.size))
    }

    @Test
    fun loadMovieDetail() {
        onView(withId(R.id.list)).check(matches(isDisplayed()))
        onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
        onView(withId(R.id.judulItemDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.judulItemDetail)).check(matches(withText(dataMovie[0].title)))
        onView(withId(R.id.DescDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.DescDetail)).check(matches(withText(dataMovie[0].overview)))
        onView(withId(R.id.imageDetail)).check(matches(isDisplayed()))
    }
    @Test
    fun loadTvList(){
        onView(withText("TV")).perform(click())
        onView(withId(R.id.listtv)).check(matches(isDisplayed()))
        onView(withId(R.id.listtv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataTv.size))
    }

    @Test
    fun loadDetailTv(){
        onView(withText("TV")).perform(click())
        onView(withId(R.id.listtv)).check(matches(isDisplayed()))
        onView(withId(R.id.listtv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.judulItemDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.judulItemDetail)).check(matches(withText(dataTv[0].name)))
        onView(withId(R.id.DescDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.DescDetail)).check(matches(withText(dataTv[0].overview)))
        onView(withId(R.id.imageDetail)).check(matches(isDisplayed()))
    }


}