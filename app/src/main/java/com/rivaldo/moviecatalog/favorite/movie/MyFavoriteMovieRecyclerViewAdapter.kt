package com.rivaldo.moviecatalog.favorite.movie

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.rivaldo.moviecatalog.R
import com.rivaldo.moviecatalog.database.Movie

import com.rivaldo.moviecatalog.favorite.movie.dummy.DummyContent.DummyItem
import com.rivaldo.moviecatalog.utils.FavoriteItemClicked
import com.squareup.picasso.Picasso

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyFavoriteMovieRecyclerViewAdapter: PagedListAdapter<Movie, MyFavoriteMovieRecyclerViewAdapter.ViewHolder>(DIFF_CALLBACK){

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>(){
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    private lateinit var interfaceItemClicked : FavoriteItemClicked

    fun setInterfaceItemClicked(interfaceitemclicked : FavoriteItemClicked) {
        this.interfaceItemClicked = interfaceitemclicked
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_favorite_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.judul.text = item?.title
        holder.desc.text = item?.desc
        Picasso.get().load("https://image.tmdb.org/t/p/w500/"+item?.image).into(holder.imageMovie)
        holder.buttondelete.setOnClickListener { interfaceItemClicked.onMovieDeleteClicked(movie = item!!) }
    }



    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageMovie: ImageView = view.findViewById(R.id.imagemovie)
        val judul: TextView = view.findViewById(R.id.txtjudul)
        val desc: TextView = view.findViewById(R.id.txtdesc)
        val buttondelete : ImageButton = view.findViewById(R.id.delete)

    }
}