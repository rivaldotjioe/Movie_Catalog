package com.rivaldo.moviecatalog.movielist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.rivaldo.moviecatalog.R
import com.rivaldo.moviecatalog.source.remote.response.ResultsItemMoviePopular
import com.rivaldo.moviecatalog.utils.MovieItemClicked
import com.squareup.picasso.Picasso


/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyMovieListRecyclerViewAdapter(
    private val values: List<ResultsItemMoviePopular>
) : RecyclerView.Adapter<MyMovieListRecyclerViewAdapter.ViewHolder>() {

    private lateinit var interfaceMovieItemClicked : MovieItemClicked

    fun setInterfaceItemClicked(interfaceMovieItemClicked: MovieItemClicked) {
        this.interfaceMovieItemClicked = interfaceMovieItemClicked
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        with(holder){
            judul.text = item.title
            desc.text = item.overview
            //set image
            Picasso.get().load("https://image.tmdb.org/t/p/w500/"+item.posterPath).into(imageMovie)
            imageMovie.tag = item.posterPath
            itemView.setOnClickListener {
                interfaceMovieItemClicked.onItemMovieClicked(item)
            }
        }

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageMovie: ImageView = view.findViewById(R.id.imagemovie)
        val judul: TextView = view.findViewById(R.id.txtjudul)
        val desc: TextView = view.findViewById(R.id.txtdesc)

        override fun toString(): String {
            return super.toString() + " '" + desc.text + "'"
        }
    }
}