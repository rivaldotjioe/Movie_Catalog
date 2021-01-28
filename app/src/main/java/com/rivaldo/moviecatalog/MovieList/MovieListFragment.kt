package com.rivaldo.moviecatalog.MovieList

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
import com.rivaldo.moviecatalog.Data.Movie
import com.rivaldo.moviecatalog.Data.Tv
import com.rivaldo.moviecatalog.Detail.DetailActivity
import com.rivaldo.moviecatalog.R
import com.rivaldo.moviecatalog.Utils.MovieItemClicked

/**
 * A fragment representing a list of Items.
 */
class MovieListFragment : Fragment() {

    private var columnCount = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        val movieViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MovieViewModel::class.java]
        var movieadapter = MyMovieListRecyclerViewAdapter(movieViewModel.getMovie())
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = movieadapter

            }
            movieadapter.setInterfaceItemClicked(object : MovieItemClicked{
                override fun onItemMovieClicked(data: Movie) {
                    val intent = Intent(activity, DetailActivity::class.java)
                    intent.putExtra("data_movie", data)
                    intent.setType(DetailActivity.MOVIE)
                    startActivity(intent)
                }

            })
        }
        return view
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