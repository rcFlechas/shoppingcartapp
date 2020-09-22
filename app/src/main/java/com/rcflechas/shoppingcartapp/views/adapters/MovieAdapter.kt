package com.rcflechas.shoppingcartapp.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.rcflechas.shoppingcartapp.R
import com.rcflechas.shoppingcartapp.core.onClick
import com.rcflechas.shoppingcartapp.models.data.remote.rest.TheMovieDB
import com.rcflechas.shoppingcartapp.views.binds.MovieBind
import com.rcflechas.shoppingcartapp.views.widget.GlideApp
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter (val clickClosure: (MovieBind) -> Unit) : CustomAdapter<MovieBind, MovieAdapter.ViewHolder>() {

    private var dataItems = arrayListOf<MovieBind>()

    fun setData(movies: MutableList<MovieBind>) {

        dataItems.clear()
        dataItems.addAll(movies)
        elements.clear()
        elements.addAll(movies)
        notifyDataSetChanged()
    }

    fun getData(): MutableList<MovieBind> {
        return dataItems
    }

    fun clearData() {
        this.dataItems.clear()
        this.elements.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return dataItems.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = dataItems[position]
        holder.bind(movie)
        holder.bindClick(movie)
    }

    override fun getItemId(position: Int): Long {
        return dataItems[position].id.toLong()
    }

    fun getItem(position: Int): MovieBind {
        return dataItems[position]
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: MovieBind) {
            setImageForUrl(movie.posterPath, itemView)
            itemView.titleMovieTextView.text = movie.title
            itemView.overViewMovieTextView.text = movie.overView
        }

        fun bindClick(movie: MovieBind) {

            itemView.addMaterialButton.onClick {
                itemView.addLinearLayoutCompat.visibility = View.GONE
                itemView.addRemoveLinearLayoutCompat.visibility = View.VISIBLE
            }

            itemView.onClick {
                clickClosure(movie)
            }
        }
    }

    private fun setImageForUrl(url: String, itemView: View) {
        val options = RequestOptions()
            .centerCrop()

        GlideApp.with(itemView.context)
            .load("${TheMovieDB.URL_IMAGE_W92}${url}")
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .apply(options)
            .into(itemView.movieImageView)
    }
}