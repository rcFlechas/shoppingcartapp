package com.rcflechas.shoppingcartapp.views.binds

import java.io.Serializable

data class MovieWithCartBind (
    val cart: CartBind,
    val movie: MovieBind
) : Serializable {

    companion object {
        fun getMovies (cartWithMovies: List<MovieWithCartBind>) : List<MovieBind> {

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