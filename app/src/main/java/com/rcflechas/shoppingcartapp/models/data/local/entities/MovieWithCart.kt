package com.rcflechas.shoppingcartapp.models.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.rcflechas.shoppingcartapp.views.binds.CartBind
import com.rcflechas.shoppingcartapp.views.binds.MovieWithCartBind
import java.io.Serializable

data class MovieWithCart (

    @Embedded val movie: Movie,
    @Relation(
        parentColumn = "movi_id",
        entityColumn = "movi_id"
    )
    val cart: Cart?
) : Serializable {

    companion object {
        fun mapperMovieWithCartToMovieWithCartBindList(movieWithCart: List<MovieWithCart>): List<MovieWithCartBind> {

            val list = mutableListOf<MovieWithCartBind>()
            movieWithCart.forEach {
                list.add(
                    MovieWithCartBind(
                        cart = Cart.mapperCartToCartBind(it.cart ?: Cart()),
                        movie = Movie.mapperMovieEntityToMovieBind(it.movie)
                    )
                )
            }
            return list.toList()
        }
    }
}