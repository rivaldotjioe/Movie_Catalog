package com.rivaldo.moviecatalog.favorite.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.rivaldo.moviecatalog.R
import com.rivaldo.moviecatalog.database.Movie
import com.rivaldo.moviecatalog.databinding.FragmentFavoriteMovieBinding
import com.rivaldo.moviecatalog.databinding.FragmentMovieListBinding
import com.rivaldo.moviecatalog.favorite.movie.dummy.DummyContent
import com.rivaldo.moviecatalog.movielist.MovieViewModel
import com.rivaldo.moviecatalog.utils.FavoriteItemClicked
import com.rivaldo.moviecatalog.viewmodel.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

/**
 * A fragment representing a list of Items.
 */
class FavoriteMovieFragment : Fragment() {

    private var columnCount = 1
    private lateinit var viewBinding : FragmentFavoriteMovieBinding
    private lateinit var movieViewModel: FavoriteMovieViewModel
    private var _binding : FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        val view = binding.root
        val factory = ViewModelFactory.getInstance(requireActivity())
        movieViewModel = ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]
        // Set the adapter
        movieViewModel.getFavoriteMovie()?.observe(this@FavoriteMovieFragment, {
            var favoritemovieadapter = MyFavoriteMovieRecyclerViewAdapter(it)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                    adapter = favoritemovieadapter

            }
        }
            favoritemovieadapter.setInterfaceItemClicked(object : FavoriteItemClicked{
                override fun onMovieDeleteClicked(movie: Movie) {
                    GlobalScope.launch(Dispatchers.IO) {
                        movieViewModel.deleteFavoriteMovie(movie)
                    }
                    favoritemovieadapter.notifyDataSetChanged()
                }
            })
        })
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            FavoriteMovieFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}