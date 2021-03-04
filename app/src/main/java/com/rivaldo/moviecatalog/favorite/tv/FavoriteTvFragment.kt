package com.rivaldo.moviecatalog.favorite.tv

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
import com.rivaldo.moviecatalog.database.Tv
import com.rivaldo.moviecatalog.databinding.FragmentFavoriteTvBinding
import com.rivaldo.moviecatalog.databinding.FragmentItemFavoriteTvBinding
import com.rivaldo.moviecatalog.databinding.FragmentTvListBinding
import com.rivaldo.moviecatalog.favorite.tv.dummy.DummyContent
import com.rivaldo.moviecatalog.utils.FavoriteItemClicked
import com.rivaldo.moviecatalog.viewmodel.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

/**
 * A fragment representing a list of Items.
 */
class FavoriteTvFragment : Fragment() {

    private var columnCount = 1
    private lateinit var viewBinding : FragmentFavoriteTvBinding
    private lateinit var tvViewModel: FavoriteTvViewModel
    private var _binding : FragmentItemFavoriteTvBinding? = null
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
        _binding = FragmentItemFavoriteTvBinding.inflate(inflater, container, false)

        val view = binding.root

        val factory = ViewModelFactory.getInstance(requireActivity())
        tvViewModel = ViewModelProvider(this, factory)[FavoriteTvViewModel::class.java]

        tvViewModel.getFavoriteTv()?.observe(this@FavoriteTvFragment, {
            var favoritetvadapter = MyFavoriteTvRecyclerViewAdapter()
            favoritetvadapter.submitList(it)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = favoritetvadapter
            }
        }

            favoritetvadapter.setInterfaceItemClicked(object : FavoriteItemClicked {
                override fun onMovieDeleteClicked(movie: Movie) {

                }

                override fun onTvDeleteClicked(tv: Tv) {
                    GlobalScope.launch(Dispatchers.IO) {
                        tvViewModel.deleteFavoriteTv(tv)
                    }
                    favoritetvadapter.notifyDataSetChanged()
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
            FavoriteTvFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}