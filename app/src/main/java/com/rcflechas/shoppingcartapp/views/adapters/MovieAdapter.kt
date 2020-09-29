package com.rcflechas.shoppingcartapp.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.rcflechas.shoppingcartapp.R
import com.rcflechas.shoppingcartapp.core.onClick
import com.rcflechas.shoppingcartapp.core.setImageByUrl
import com.rcflechas.shoppingcartapp.models.data.local.entities.CartWithMovie
import com.rcflechas.shoppingcartapp.models.data.remote.rest.TheMovieDB
import com.rcflechas.shoppingcartapp.views.binds.MovieBind
import com.rcflechas.shoppingcartapp.views.binds.MovieWithCartBind
import com.rcflechas.shoppingcartapp.views.widget.GlideApp
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter (
    val clickClosure: (MovieWithCartBind) -> Unit,
    val addClosure: (MovieWithCartBind) -> Unit,
    val removeClosure: (MovieWithCartBind) -> Unit
) : CustomAdapter<MovieWithCartBind, MovieAdapter.ViewHolder>() {

    private var dataItems = arrayListOf<MovieWithCartBind>()

    fun setData(movieWithCartBind: MutableList<MovieWithCartBind>) {

        dataItems.clear()
        dataItems.addAll(movieWithCartBind)
        elements.clear()
        elements.addAll(movieWithCartBind)
        notifyDataSetChanged()
    }

    fun getData(): MutableList<MovieWithCartBind> {
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
        val movieWithCartBind = dataItems[position]
        holder.bind(movieWithCartBind)
        holder.bindClick(movieWithCartBind)
    }

    override fun getItemId(position: Int): Long {
        return dataItems[position].movie.id.toLong()
    }

    fun getItem(position: Int): MovieWithCartBind {
        return dataItems[position]
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movieWithCartBind: MovieWithCartBind) {

            itemView.movieImageView.setImageByUrl(
                url = "${TheMovieDB.URL_IMAGE_W780}${movieWithCartBind.movie.posterPath}",
                options = RequestOptions().centerCrop()
            )
            itemView.titleMovieTextView.text = movieWithCartBind.movie.title
            itemView.overViewMovieTextView.text = movieWithCartBind.movie.overView
            itemView.countTextView.text = movieWithCartBind.cart.amount.toString()

            if (movieWithCartBind.cart.amount > 0) {

                itemView.addLinearLayoutCompat.visibility = View.GONE
                itemView.addRemoveLinearLayoutCompat.visibility = View.VISIBLE
            } else {

                itemView.addLinearLayoutCompat.visibility = View.VISIBLE
                itemView.addRemoveLinearLayoutCompat.visibility = View.GONE
            }
        }

        fun bindClick(movieWithCartBind: MovieWithCartBind) {

            itemView.addMaterialButton.onClick {

                itemView.addLinearLayoutCompat.visibility = View.GONE
                itemView.addRemoveLinearLayoutCompat.visibility = View.VISIBLE

                movieWithCartBind.cart.amount++
                addClosure(movieWithCartBind)
            }

            itemView.onClick {
                clickClosure(movieWithCartBind)
            }

            itemView.addImageButton.onClick {

                movieWithCartBind.cart.amount++
                addClosure(movieWithCartBind)
            }

            itemView.removeImageButton.onClick {
                if (movieWithCartBind.cart.amount > 0) {

                    movieWithCartBind.cart.amount--
                    if (movieWithCartBind.cart.amount == 0) {
                        itemView.addLinearLayoutCompat.visibility = View.VISIBLE
                        itemView.addRemoveLinearLayoutCompat.visibility = View.GONE
                    }
                    removeClosure(movieWithCartBind)
                }
            }
        }
    }
}