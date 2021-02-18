package com.rivaldo.moviecatalog.tvlist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.rivaldo.moviecatalog.R
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemPopularTv
import com.rivaldo.moviecatalog.utils.TvItemClicked
import com.squareup.picasso.Picasso


/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyTvListRecyclerViewAdapter(
        private val values: List<ResultsItemPopularTv>
)
    : RecyclerView.Adapter<MyTvListRecyclerViewAdapter.ViewHolder>() {

    private lateinit var interfaceTvItemClicked : TvItemClicked

    fun setInterfaceTvItemClicked(interfaceTvItemClicked: TvItemClicked) {
        this.interfaceTvItemClicked = interfaceTvItemClicked
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_item_tv, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        with(holder) {
            viewJudul.text = item.name
            viewDesc.text = item.overview
            Picasso.get().load("https://image.tmdb.org/t/p/w500/"+item.posterPath).into(viewImage)
            itemView.setOnClickListener {
                interfaceTvItemClicked.onTvItemClicked(item)
            }
        }


    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
       val viewJudul : TextView = view.findViewById(R.id.txtjudultv)
        val viewDesc : TextView = view.findViewById(R.id.txtdesctv)
        val viewImage: ImageView = view.findViewById(R.id.imagetv)


    }
}