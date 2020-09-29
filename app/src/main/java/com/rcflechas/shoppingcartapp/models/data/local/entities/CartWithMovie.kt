package com.rcflechas.shoppingcartapp.models.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.rcflechas.shoppingcartapp.views.binds.MovieWithCartBind
import java.io.Serializable

data class CartWithMovie (

    @Embedded val cart: Cart,
    @Relation(
        parentColumn = "movi_id",
        entityColumn = "movi_id"
    )
    val movie: Movie
) :  Serializable {

    companion object {
        fun mapperCartWithMovieToCartWithMovieBindList(cartWithMovie: List<CartWithMovie>): List<MovieWithCartBind> {

            val list = mutableListOf<MovieWithCartBind>()
            cartWithMovie.forEach {
                list.add(
                    MovieWithCartBind(
                        cart = Cart.mapperCartToCartBind(it.cart),
                        movie = Movie.mapperMovieEntityToMovieBind(it.movie)
                    )
                )
            }
            return list.toList()
        }
    }
}