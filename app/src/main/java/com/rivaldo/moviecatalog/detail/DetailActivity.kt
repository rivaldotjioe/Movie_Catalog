package com.rivaldo.moviecatalog.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.rivaldo.moviecatalog.data.Movie
import com.rivaldo.moviecatalog.data.Tv
import com.rivaldo.moviecatalog.databinding.ActivityDetailBinding
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemMoviePopular
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemPopularTv
import com.rivaldo.moviecatalog.viewmodel.ViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.coroutines.InternalCoroutinesApi

class DetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE = "movie"
        const val TV = "tv"
        const val ID = "id"
    }
    private lateinit var binding : ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ViewModelFactory.getInstance(this)
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        binding = ActivityDetailBinding.inflate(layoutInflater)
        if (intent.type == MOVIE){
            val id = intent.getIntExtra(ID,0)
            detailViewModel.getDetailMovie(id)
        } else if (intent.type == TV) {
            val id = intent.getIntExtra(ID, 0)
            detailViewModel.getDetailTv(id)
        }
        if (intent.type == MOVIE){
            detailViewModel.detailmovie?.observe(this,  { movie_obtained ->
            binding.judulItemDetail.text = movie_obtained.title
            binding.DescDetail.text = movie_obtained.overview
            Picasso.get().load("https://image.tmdb.org/t/p/w500/"+movie_obtained.posterPath).into(binding.imageDetail)
            })
        } else if (intent.type == TV) {
            detailViewModel.detailtv?.observe(this, { tv_obtained ->
            binding.judulItemDetail.text = tv_obtained.name
            binding.DescDetail.text = tv_obtained.overview
            Picasso.get().load("https://image.tmdb.org/t/p/w500/"+tv_obtained.posterPath).into(binding.imageDetail)
            })
        }

        setContentView(binding.root)

    }
}