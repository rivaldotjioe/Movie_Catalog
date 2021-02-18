package com.rivaldo.moviecatalog.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rivaldo.moviecatalog.injection.Injection
import com.rivaldo.moviecatalog.movielist.MovieViewModel
import com.rivaldo.moviecatalog.source.CatalogRepository
import com.rivaldo.moviecatalog.tvlist.TvViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

class ViewModelFactory private constructor(private val catalogRepository: CatalogRepository) : ViewModelProvider.NewInstanceFactory(){

    companion object {
        @Volatile
        private var instance : ViewModelFactory? = null

        @InternalCoroutinesApi
        fun getInstance(context: Context) : ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) ->  {
                return MovieViewModel(catalogRepository) as T
            }

            modelClass.isAssignableFrom(TvViewModel::class.java) -> {
                return TvViewModel(catalogRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}