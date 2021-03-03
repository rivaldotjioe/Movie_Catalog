package com.rivaldo.moviecatalog.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.rivaldo.moviecatalog.R
import com.rivaldo.moviecatalog.data.Tv
import com.rivaldo.moviecatalog.database.Movie
import com.rivaldo.moviecatalog.databinding.ActivityDetailBinding
import com.rivaldo.moviecatalog.viewmodel.ViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*

class DetailActivity : AppCompatActivity() {

    lateinit var currentType : String
    lateinit var movie : Movie
    lateinit var tv : Tv


    companion object {
        const val MOVIE = "movie"
        const val TV = "tv"
        const val ID = "id"
    }
    private lateinit var binding : ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private var menu: Menu? = null
    private var stateFavorite : Boolean? = null

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ViewModelFactory.getInstance(this)
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        binding = ActivityDetailBinding.inflate(layoutInflater)
        if (intent.type == MOVIE){
            val id = intent.getIntExtra(ID,0)
            detailViewModel.getDetailMovie(id)
         GlobalScope.launch(Dispatchers.IO){
             val state = async {  checkFavoriteState(id) }
             Log.e("DetailActivity", state.await().toString())
             stateFavorite = state.await()
         }

        } else if (intent.type == TV) {
            val id = intent.getIntExtra(ID, 0)
            detailViewModel.getDetailTv(id)
            setCheckedFavoriteState(detailViewModel.checkFavoriteMovie(id))
        }

        if (intent.type == MOVIE){
            detailViewModel.detailmovie?.observe(this,  { movie_obtained ->
            binding.judulItemDetail.text = movie_obtained.title
            binding.DescDetail.text = movie_obtained.overview
            Picasso.get().load("https://image.tmdb.org/t/p/w500/"+movie_obtained.posterPath).into(binding.imageDetail)
                this.movie = Movie(movie_obtained.id!!, movie_obtained.title!!, movie_obtained.overview!!, movie_obtained.posterPath.toString())
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        this.menu = menu
        setCheckedFavoriteState(this.stateFavorite)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.add_favorite -> {
                detailViewModel.insertFavoriteMovie(this.movie)
                setCheckedFavoriteState(true)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun checkFavoriteState(id: Int) : Boolean {
        return detailViewModel.checkFavoriteMovie(id)
    }

    private fun setCheckedFavoriteState(state: Boolean?) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.add_favorite)
        if (state == true) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this , R.drawable.ic_favorite_border)
        }
    }
}