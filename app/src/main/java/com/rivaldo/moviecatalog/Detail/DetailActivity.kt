package com.rivaldo.moviecatalog.Detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rivaldo.moviecatalog.Data.Movie
import com.rivaldo.moviecatalog.Data.Tv
import com.rivaldo.moviecatalog.R
import com.rivaldo.moviecatalog.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE = "movie"
        const val TV = "tv"
    }
    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.type == MOVIE){
            val movie_obtained = intent.getParcelableExtra<Movie>("data_movie") as Movie
            binding.judulItemDetail.text = movie_obtained.title
            binding.DescDetail.text = movie_obtained.desc
            binding.imageDetail.setImageResource(movie_obtained.image)
        } else if (intent.type == TV) {
            val tv_obtained = intent.getParcelableExtra<Tv>("data_tv") as Tv
            binding.judulItemDetail.text = tv_obtained.name
            binding.DescDetail.text = tv_obtained.desc
            binding.imageDetail.setImageResource(tv_obtained.image)
        }

    }
}