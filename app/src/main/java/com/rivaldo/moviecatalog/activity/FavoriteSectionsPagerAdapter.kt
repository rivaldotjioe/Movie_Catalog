package com.rivaldo.moviecatalog.activity

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.rivaldo.moviecatalog.R
import com.rivaldo.moviecatalog.favorite.movie.FavoriteMovieFragment
import com.rivaldo.moviecatalog.favorite.tv.FavoriteTvFragment

class FavoriteSectionsPagerAdapter(private val mContext : Context, fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.Film, R.string.Tv)
    }
    override fun getCount(): Int = TAB_TITLES.size

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> FavoriteMovieFragment()
            1 -> FavoriteTvFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence? = mContext.resources.getString(FavoriteSectionsPagerAdapter.TAB_TITLES[position])

}