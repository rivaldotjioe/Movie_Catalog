package com.rivaldo.moviecatalog.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rivaldo.moviecatalog.R
import com.rivaldo.moviecatalog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sectionPagerAdapter = SectionsPagerAdapter(this , supportFragmentManager)
        binding.viewPager.adapter = sectionPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        supportActionBar?.elevation = 0f
    }
}