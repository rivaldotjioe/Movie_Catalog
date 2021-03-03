package com.rivaldo.moviecatalog.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rivaldo.moviecatalog.R
import com.rivaldo.moviecatalog.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sectionPagerAdapter = FavoriteSectionsPagerAdapter(this , supportFragmentManager)
        binding.viewPager.adapter = sectionPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        supportActionBar?.elevation = 0f
    }
}