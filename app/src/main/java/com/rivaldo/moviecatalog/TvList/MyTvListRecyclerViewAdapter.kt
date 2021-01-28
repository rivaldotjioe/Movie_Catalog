package com.rivaldo.moviecatalog.TvList

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.rivaldo.moviecatalog.Data.Tv
import com.rivaldo.moviecatalog.R
import com.rivaldo.moviecatalog.Utils.TvItemClicked


/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyTvListRecyclerViewAdapter(
        private val values: List<Tv>)
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
            viewDesc.text = item.desc
            viewImage.setImageResource(item.image)
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