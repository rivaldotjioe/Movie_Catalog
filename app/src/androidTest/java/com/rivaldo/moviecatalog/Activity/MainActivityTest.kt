package com.rivaldo.moviecatalog.Activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.rivaldo.moviecatalog.MovieList.MovieData
import com.rivaldo.moviecatalog.R
import com.rivaldo.moviecatalog.TvList.TvData
import com.rivaldo.moviecatalog.databinding.FragmentMovieListBinding
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class MainActivityTest {

    private val dataMovie = MovieData.generateMovieData()
    private val dataTv = TvData.generateTvData()

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun loadDataMovie(){
        onView(withId(R.id.list)).check(matches(isDisplayed()))
        onView(withId(R.id.list)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataMovie.size))
    }

    @Test
    fun loadMovieDetail() {
        onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
        onView(withId(R.id.judulItemDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.judulItemDetail)).check(matches(withText(dataMovie[0].title)))
        onView(withId(R.id.DescDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.DescDetail)).check(matches(withText(dataMovie[0].desc)))
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
        onView(withId(R.id.DescDetail)).check(matches(withText(dataTv[0].desc)))
        onView(withId(R.id.imageDetail)).check(matches(isDisplayed()))
    }


}