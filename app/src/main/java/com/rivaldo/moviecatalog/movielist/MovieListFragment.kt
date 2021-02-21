package com.rivaldo.moviecatalog.movielist

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.rivaldo.moviecatalog.detail.DetailActivity
import com.rivaldo.moviecatalog.detail.DetailViewModel
import com.rivaldo.moviecatalog.databinding.FragmentMovieListBinding
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemMoviePopular
import com.rivaldo.moviecatalog.utils.MovieItemClicked
import com.rivaldo.moviecatalog.viewmodel.ViewModelFactory
import kotlinx.coroutines.*

/**
 * A fragment representing a list of Items.
 */
class MovieListFragment : Fragment() {

    private var columnCount = 1
    private lateinit var viewBinding : FragmentMovieListBinding
    private lateinit var movieViewModel: MovieViewModel
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
        movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            movieViewModel.getPopularMovie()


        movieViewModel.moviepopular?.observe(this, {
            var movieadapter = MyMovieListRecyclerViewAdapter(it)
            // Set the adapter

        if (true) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                    adapter = movieadapter
            }
            movieadapter.setInterfaceItemClicked(object : MovieItemClicked{
                override fun onItemMovieClicked(data: ResultsItemMoviePopular) {
                    val intent = Intent(activity, DetailActivity::class.java)
                    intent.setType(DetailActivity.MOVIE)
                    intent.putExtra(DetailActivity.ID, data.id)
                    startActivity(intent)
                }
            })
         }
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"
        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            MovieListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}