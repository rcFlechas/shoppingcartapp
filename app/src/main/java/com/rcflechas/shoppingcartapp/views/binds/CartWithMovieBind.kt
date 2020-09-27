package com.rcflechas.shoppingcartapp.views.binds

import com.rcflechas.shoppingcartapp.models.data.local.entities.Cart
import java.io.Serializable

data class CartWithMovieBind (
    val cart: CartBind,
    val movie: MovieBind
) : Serializable {

    companion object {
        fun getMovies (cartWithMovies: List<CartWithMovieBind>) : List<MovieBind> {

            val list = mutableListOf<MovieBind>()
            cartWithMovies.forEach {
                list.add(
                    MovieBind(
                        id = it.movie.id,
                        title = it.movie.title,
                        overView = it.movie.overView,
                        posterPath = it.movie.posterPath,
                        backdropPath = it.movie.backdropPath,
                        isAdult = it.movie.isAdult
                    )
                )
            }
            return list.toList()
        }
    }
}