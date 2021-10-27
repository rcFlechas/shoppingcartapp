package com.rcflechas.shoppingcartapp.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.DiffResult.NO_POSITION
import com.bumptech.glide.request.RequestOptions
import com.rcflechas.shoppingcartapp.core.onClick
import com.rcflechas.shoppingcartapp.core.setImageByUrl
import com.rcflechas.shoppingcartapp.databinding.ItemMovieBinding
import com.rcflechas.shoppingcartapp.models.data.remote.rest.TheMovieDB
import com.rcflechas.shoppingcartapp.views.binds.MovieWithCartBind

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

        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ViewHolder(binding)

        binding.root.onClick {

            val position = holder.adapterPosition.takeIf { it != NO_POSITION } ?: return@onClick
            clickClosure(elements[position])
        }

        binding.addMaterialButton.onClick {

            val position = holder.adapterPosition.takeIf { it != NO_POSITION } ?: return@onClick
            val movieWithCartBind = elements[position]
            binding.addLinearLayoutCompat.visibility = View.GONE
            binding.addRemoveLinearLayoutCompat.visibility = View.VISIBLE

            movieWithCartBind.cart.amount++
            addClosure(movieWithCartBind)
        }

        binding.addImageButton.onClick {

            val position = holder.adapterPosition.takeIf { it != NO_POSITION } ?: return@onClick
            val movieWithCartBind = elements[position]
            movieWithCartBind.cart.amount++
            addClosure(movieWithCartBind)
        }

        binding.removeImageButton.onClick {

            val position = holder.adapterPosition.takeIf { it != NO_POSITION } ?: return@onClick
            val movieWithCartBind = elements[position]
            if (movieWithCartBind.cart.amount > 0) {

                movieWithCartBind.cart.amount--
                if (movieWithCartBind.cart.amount == 0) {
                    binding.addLinearLayoutCompat.visibility = View.VISIBLE
                    binding.addRemoveLinearLayoutCompat.visibility = View.GONE
                }
                removeClosure(movieWithCartBind)
            }
        }

        return holder
    }

    override fun getItemCount(): Int {
        return dataItems.count()
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val movieWithCartBind = elements[position]

        when (holder) {
            is ViewHolder -> holder.bind(movieWithCartBind)
        }
    }

    override fun getItemId(position: Int): Long {
        return elements[position].movie.id.toLong()
    }

    fun getItem(position: Int): MovieWithCartBind {
        return elements[position]
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) : BaseViewHolder<MovieWithCartBind>(binding.root) {

        override fun bind(item: MovieWithCartBind) {

            binding.movieImageView.setImageByUrl(
                url = "${TheMovieDB.URL_IMAGE_W780}${item.movie.posterPath}",
                options = RequestOptions().centerCrop()
            )
            binding.titleMovieTextView.text = item.movie.title
            binding.overViewMovieTextView.text = item.movie.overView
            binding.countTextView.text = item.cart.amount.toString()

            if (item.cart.amount > 0) {

                binding.addLinearLayoutCompat.visibility = View.GONE
                binding.addRemoveLinearLayoutCompat.visibility = View.VISIBLE
            } else {

                binding.addLinearLayoutCompat.visibility = View.VISIBLE
                binding.addRemoveLinearLayoutCompat.visibility = View.GONE
            }
        }
     }
}