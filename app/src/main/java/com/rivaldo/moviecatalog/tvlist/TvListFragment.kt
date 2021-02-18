package com.rivaldo.moviecatalog.tvlist

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
import com.rivaldo.moviecatalog.databinding.FragmentTvListBinding
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemPopularTv
import com.rivaldo.moviecatalog.utils.TvItemClicked
import com.rivaldo.moviecatalog.viewmodel.ViewModelFactory
import kotlinx.coroutines.InternalCoroutinesApi

/**
 * A fragment representing a list of Items.
 */
class TvListFragment : Fragment() {

    private var columnCount = 1
    private lateinit var tvViewModel: TvViewModel
    private var _binding : FragmentTvListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    @InternalCoroutinesApi
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentTvListBinding.inflate(inflater, container, false)
        val view = binding.root
        val factory = ViewModelFactory.getInstance(requireActivity())
        tvViewModel = ViewModelProvider(this, factory)[TvViewModel::class.java]
        tvViewModel.getPopularTv()
        tvViewModel.tvPopular?.observe(this, {
        val tvadapter = MyTvListRecyclerViewAdapter(it)
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = tvadapter
            }
            tvadapter.setInterfaceTvItemClicked(object : TvItemClicked {
                override fun onTvItemClicked(data: ResultsItemPopularTv) {
                    val intent = Intent(activity, DetailActivity::class.java)
                    DetailViewModel.setData(data)
                    intent.setType(DetailActivity.TV)
                    startActivity(intent)
                }
            })
        }
        })
        return view
    }


    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
                TvListFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }
}