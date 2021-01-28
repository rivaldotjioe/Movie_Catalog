package com.rivaldo.moviecatalog.TvList

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
import com.rivaldo.moviecatalog.Utils.TvItemClicked
import java.util.ArrayList

/**
 * A fragment representing a list of Items.
 */
class TvListFragment : Fragment() {

    private var columnCount = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tv_list, container, false)
        val tvViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[TvViewModel::class.java]
        val tvadapter = MyTvListRecyclerViewAdapter(tvViewModel.getTvData())
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
                override fun onTvItemClicked(data: Tv) {
                    val intent = Intent(activity, DetailActivity::class.java)
                    intent.putExtra("data_tv", data)
                    intent.setType(DetailActivity.TV)
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
                TvListFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }
}