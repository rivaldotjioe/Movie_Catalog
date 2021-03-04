package com.rivaldo.moviecatalog.favorite.tv

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.rivaldo.moviecatalog.R
import com.rivaldo.moviecatalog.database.Tv

import com.rivaldo.moviecatalog.favorite.tv.dummy.DummyContent.DummyItem
import com.rivaldo.moviecatalog.utils.FavoriteItemClicked
import com.squareup.picasso.Picasso

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyFavoriteTvRecyclerViewAdapter: PagedListAdapter<Tv, MyFavoriteTvRecyclerViewAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Tv>() {
            override fun areContentsTheSame(oldItem: Tv, newItem: Tv): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: Tv, newItem: Tv): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    private lateinit var interfaceItemClicked: FavoriteItemClicked

    fun setInterfaceItemClicked(interfaceitemclicked: FavoriteItemClicked) {
        this.interfaceItemClicked = interfaceitemclicked
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_favorite_tv, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.judul.text = item?.name
        holder.desc.text = item?.desc
        Picasso.get().load("https://image.tmdb.org/t/p/w500/"+item?.image).into(holder.imagetv)
        holder.buttondelete.setOnClickListener { interfaceItemClicked.onTvDeleteClicked(tv = item!!) }
    }



    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imagetv: ImageView = view.findViewById(R.id.imagetvfavorite)
        val judul: TextView = view.findViewById(R.id.txttvjudul)
        val desc: TextView = view.findViewById(R.id.txttvdesc)
        val buttondelete : ImageButton = view.findViewById(R.id.deletetv)


    }
}