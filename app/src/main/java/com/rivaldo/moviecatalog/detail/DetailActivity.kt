package com.rivaldo.moviecatalog.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.rivaldo.moviecatalog.data.Movie
import com.rivaldo.moviecatalog.data.Tv
import com.rivaldo.moviecatalog.databinding.ActivityDetailBinding
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemMoviePopular
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemPopularTv
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE = "movie"
        const val TV = "tv"
    }
    private lateinit var binding : ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]
        if (intent.type == MOVIE){
            val movie_obtained = detailViewModel.getData() as ResultsItemMoviePopular
            binding.judulItemDetail.text = movie_obtained.title
            binding.DescDetail.text = movie_obtained.overview
            Picasso.get().load("https://image.tmdb.org/t/p/w500/"+movie_obtained.posterPath).into(binding.imageDetail)

        } else if (intent.type == TV) {
            val tv_obtained = detailViewModel.getData() as ResultsItemPopularTv
            binding.judulItemDetail.text = tv_obtained.name
            binding.DescDetail.text = tv_obtained.overview
            Picasso.get().load("https://image.tmdb.org/t/p/w500/"+tv_obtained.posterPath).into(binding.imageDetail)
        }

    }
}